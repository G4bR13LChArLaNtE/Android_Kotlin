package br.com.mobile.infocountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pais_cadastro.*
import kotlinx.android.synthetic.main.activity_pais_cadastro.nomePais
import kotlinx.android.synthetic.main.activity_pais_remover.*

class PaisRemoverActivity : AppCompatActivity() {


    private fun taskRemover(pais: Pais) {
        Thread {
            Paisservice.delete(pais)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pais_remover)
        setTitle("Remover Pais")



        removerPais.setOnClickListener {
            val pais = Pais()
            pais.nome_pais = nomePais.text.toString()

            taskRemover(pais)
        }
    }

}