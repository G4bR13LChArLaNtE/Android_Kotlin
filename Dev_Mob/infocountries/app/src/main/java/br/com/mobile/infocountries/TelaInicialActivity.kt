package br.com.mobile.infocountries

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class TelaInicialActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val args = intent.extras
        val nome = args?.getString("nome")

        val numero = intent.getIntExtra("nome", 0)

        btn_sair.setOnClickListener{cliqueSair()}

    }

    fun cliqueSair() {
        val returnIntent = Intent();
        val args = intent.extras
        val nome = args?.getString("nome")
        returnIntent.putExtra("result", "Tchau $nome!");
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId

        if (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_adicionar) {
            Toast.makeText(context, "Botão de adicionar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
            progressAtualizar.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    progressAtualizar.visibility = View.GONE
                },
                5000 )


        } else if (id == android.R.id.home) {

            Toast.makeText(context, "Botão de sair", Toast.LENGTH_LONG).show()
            cliqueSair()
            finish()
        }



        return super.onOptionsItemSelected(item)
    }






}