package br.com.fernandosousa.lmsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        imagemLogin.setImageResource(R.drawable.imagem_login)

        // acessar campo de texto da tela
        // trocar o texto da tela
        mensagemLogin.text = "Nova mensagem de login"
    }
}