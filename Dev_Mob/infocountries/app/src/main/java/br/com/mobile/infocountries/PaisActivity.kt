package br.com.mobile.infocountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pais.*
import kotlinx.android.synthetic.main.toolbar.*

class PaisActivity : DebugActivity() {

    var pais: Pais? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pais)


        pais = intent.getSerializableExtra("pais") as Pais
            // configurar título com nome do Pais e botão de voltar da Toolbar
            // colocar toolbar
        setSupportActionBar(toolbar)
            // alterar título da ActionBar
        supportActionBar?.title = pais?.nome_pais
            // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nomePais.text = pais?.nome_pais
        Picasso.with(this).load(pais?.bandeira).fit().into(imagemPais,

            object: com.squareup.picasso.Callback{
                override fun onSuccess() {}

                override fun onError() { }
            })
    }
}