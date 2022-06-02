package br.com.mobile.infocountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pais_cadastro.*

class PaisCadastroActivity : AppCompatActivity() {

   private  fun taskAtualizar(pais: Pais) {
        // Thread para salvar o pais
        Thread {
            Paisservice.save(pais)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pais_cadastro)
        setTitle("Novo Pais")

       salvarPais.setOnClickListener {
            val pais = Pais()
            pais.nome_pais = nomePais.text.toString()

            taskAtualizar(pais)
        }

    }

}