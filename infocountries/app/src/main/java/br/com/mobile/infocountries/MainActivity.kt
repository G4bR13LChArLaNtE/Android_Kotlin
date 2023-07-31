package br.com.mobile.infocountries

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        imageView.setImageResource(R.drawable.imagem_login)

        btn_entrar.setOnClickListener { onClickLogin() }

        // procurar pelas preferências, se pediu para guardar usuário e senha
        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome  = Prefs.getString("lembrarNome")
            var lembrarSenha  = Prefs.getString("lembrarSenha")
            text_user.setText(lembrarNome)
            text_password.setText(lembrarSenha)
            checkLembrar.isChecked = lembrar

        }
    }


    fun onClickLogin() {
        val valorUsuario = text_user.text.toString()
        val valorSenha = text_password.text.toString()
        val intent = Intent(context, TelaInicialActivity::class.java)
        val params = Bundle()

        if (valorUsuario == "aluno"  && valorSenha == "impacta"){

            params.putString("nome", valorUsuario)
            intent.putExtras(params)


            Toast.makeText(this, "Olá, $valorUsuario!", Toast.LENGTH_LONG).show()


            Prefs.setBoolean("lembrar", checkLembrar.isChecked)

            if (checkLembrar.isChecked){
                Prefs.setString("lembrarNome", valorUsuario)
                Prefs.setString("lembrarSenha", valorSenha)
            } else{
                Prefs.setString("lembrarNome", "")
                Prefs.setString("lembrarSenha", "")
                text_user.setText("")
                text_password.setText("")
            }

            startActivityForResult(intent, 1)

        }else if (valorUsuario != "aluno"  || valorSenha != "impacta"){
            Toast.makeText(this, "Senha incorreta ou usuário incorreto!!!", Toast.LENGTH_SHORT).show()
            text_user.setText("")
            text_password.setText("")
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }
}