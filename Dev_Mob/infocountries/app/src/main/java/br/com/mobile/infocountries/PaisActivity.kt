package br.com.mobile.infocountries

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pais.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.toolbar.*
import java.lang.Exception


class PaisActivity : DebugActivity() {

    private val context: Context get() = this
    var pais: Pais? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pais)


       // bnt_mapa.setOnClickListener { onClickmapa() }

        pais = intent.getSerializableExtra("pais") as Pais
            // configurar título com nome do Pais e botão de voltar da Toolbar
            // colocar toolbar
        setSupportActionBar(toolbar)
            // alterar título da ActionBar
        supportActionBar?.title = pais?.nome_pais
            // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nomePais.text = "Nome: ${pais?.nome_pais}."
        capitalPais.text = "Capital: ${pais?.capital}."
        contPais.text = "Continente: ${pais?.continente}."
        popPais.text = "População: ${pais?.populacao}."


        Picasso.with(this).load(pais?.bandeira).fit().into(imagemPais,

            object: com.squareup.picasso.Callback{
                override fun onSuccess() {}
                override fun onError() {
                    TODO("Ainda não implementado!")
                }
            })
    }




    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main_pais, menu)
        return true
    }

    private fun taskExcluir() {
        if (this.pais != null && this.pais is Pais) {
            // Thread para remover o pais
            Thread {
                Paisservice.delete(this.pais as Pais)
                runOnUiThread {
                    // após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // id do item clicado
        val id = item?.itemId
            // verificar qual item foi clicado
            // remover o pais no WS
        if (id == R.id.action_remover) {
        // alerta para confirmar a remoção
            // só remove se houver confirmação positiva
            AlertDialog.Builder(this)

                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir o pais")
                .setPositiveButton("Sim") {
                        dialog, which ->
                    dialog.dismiss()
                    taskExcluir()

                }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                }.create().show()

        }
            // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)

    }


    private fun onClickmapa(){
        val intent = Intent(context, MapsActivity::class.java)
        intent.putExtra("nome_pais", "${pais?.nome_pais}")
        intent.putExtra("latitude", "${pais?.latitude}")
        intent.putExtra("longitude", "${pais?.longitude}")
        startActivity(intent)
    }

}