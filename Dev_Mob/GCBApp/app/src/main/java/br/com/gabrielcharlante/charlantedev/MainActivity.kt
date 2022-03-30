package br.com.gabrielcharlante.charlantedev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)



        imagemLogin.setImageResource(R.drawable.imagem_login)

        mensagemLogin.text = "Faça seu Login, por favor!"
        textologin.text = "Que tal estudar no melhor curso de TI?"
        textologin2.text = "Então entre na nossa plataforma mobile."
    }
}