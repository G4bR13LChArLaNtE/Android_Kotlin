package br.com.mobile.infocountries

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var paises = listOf<Pais>()
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_tela_inicial)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val args = intent.extras
            val nome = args?.getString("nome")


            // colocar toolbar
            setSupportActionBar(toolbar)


            // alterar título da ActionBar
            supportActionBar?.title = "Países"

            // up navigation
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            // configuração do menu lateral
            configuraMenuLateral()

            recyclerPaises?.layoutManager = LinearLayoutManager(context)
            recyclerPaises?.itemAnimator = DefaultItemAnimator()
            recyclerPaises?.setHasFixedSize(true)
        }

        override fun onResume() {
            super.onResume()
            taskPaises()
        }

    // tratamento do evento de clicar em um país
        fun onClickPais(pais: Pais) {
                Toast.makeText(context, "Clicou pais ${pais.nome_pais}", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, PaisActivity::class.java)
                intent.putExtra("pais", pais)
                startActivity(intent)
        }




            // task para recuperar os países
        fun taskPaises() {

                Thread {
                    this.paises = Paisservice.getPaises(context)
                    runOnUiThread {  // atualizar lista
                        recyclerPaises?.adapter = PaisesAdapter(this.paises) { onClickPais(it) } }


                }.start()

        }

    fun enviaNotificacao(pais: Pais) {
        // Intent para abrir tela quando clicar na notificação
        val intent = Intent(context, PaisActivity::class.java)
        // parâmetros extras
        intent.putExtra("pais", pais)
        // Disparar notificação
        NotificationUtil.create(context, pais.id, intent, "Infocountries", "Você tem nova atividade no Infocountries")
    }



    // configuração do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {
        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    // método que deve ser implementado quando a activity implementa a interface NavigationView.OnNavigationItemSelectedListener
    // para tratar os eventos de clique no menu lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_paises -> {
                Toast.makeText(this, "Clicou  países", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_sobre -> {
                Toast.makeText(this, "Versão do software: 1.0", Toast.LENGTH_LONG).show()

            }

            R.id.nav_sair -> {
                Toast.makeText(this, "Clicou sair", Toast.LENGTH_SHORT).show()
                cliqueSair()
            }

        }
        // fecha menu depois de tratar o evento
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }


    fun cliqueSair() {
        val returnIntent = Intent();
        val args = intent.extras
        val nome = args?.getString("nome")
        returnIntent.putExtra("result", "Tchau $nome!");
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId

        if (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_adicionar) {
            Toast.makeText(context, "Botão de adicionar", Toast.LENGTH_LONG).show()

            val intent = Intent(context, PaisCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)

        } else if (id == R.id.action_remover){

            val intent = Intent(context, PaisRemoverActivity::class.java)
            startActivityForResult(intent, REQUEST_REMOVE)

        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
            taskPaises()
        } else if (id == android.R.id.home) {
            Toast.makeText(context, "Saindo!!", Toast.LENGTH_LONG).show()
            finish()
        }



        return super.onOptionsItemSelected(item)
    }

    // esperar o retorno do cadastro do pais
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE) {
            // atualizar lista de paises
            taskPaises()
        }

    }
}