package com.example.barbershop.view

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.barbershop.R
import com.example.barbershop.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.Calendar

class Agendamento : AppCompatActivity() {


    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->


            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String

            if(dayOfMonth < 10){
                dia = "0$dayOfMonth"
            }
            if (monthOfYear <10){
                mes = "" + (monthOfYear + 1)
            }else{
                mes = (monthOfYear + 1).toString()
            }

            data = "$dia / $mes / $year"

        }
        
        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->

            val minuto: String

            if (minute < 10) {
                minuto = "0$minute"
            }else{
               minuto =  minute.toString()
            }

            hora = "$hourOfDay:$minuto"
        }

        binding.timePicker.setIs24HourView(true) //formato de 24 horas


        binding.btAgendar.setOnClickListener {

            val barbeiro1 = binding.barbeiro1
            val barbeiro2 = binding.barbeiro2
            val barbeiro3 = binding.barbeiro3

            when{
                hora.isEmpty() -> {
                    mensagem(it, "Preencha o horario!", "#FF0000")
                }
                hora < "8:00" && hora > "20:00" ->{
                    mensagem(it, "Barbearia está fechada - Horário de funcionamento dás 8:00 às 20:00", "#FF0000")
                }
                data.isEmpty() ->{
                    mensagem(it, "Coloque a data desejada!", "#FF0000")
                }
                barbeiro1.isChecked && data.isNotEmpty() && hora .isNotEmpty() ->{
                    salvarAgendamento(it, nome, "Pedro da Silva", data, hora)
                }
                barbeiro2.isChecked && data.isNotEmpty() && hora .isNotEmpty() ->{
                    salvarAgendamento(it, nome, "João Texeira", data, hora)
                }
                barbeiro3.isChecked && data.isNotEmpty() && hora .isNotEmpty() ->{
                    salvarAgendamento(it, nome, "Claudio Silveira", data, hora)
                }
                else -> {
                    mensagem(it, "Escolha um profissional!", "#FF0000")
                }
            }

        }
        
    }

    private fun mensagem(view: View, mensagem: String, cor: String){
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }

    private fun salvarAgendamento(view: View, cliente: String, barbeiro: String, data: String, hora: String){

        val db = FirebaseFirestore.getInstance()

        val dadosUsuario = hashMapOf(
            "cliente" to cliente,
            "barbeiro" to barbeiro,
            "data" to data,
            "hora" to hora
        )

        db.collection("agendamento").document(cliente).set(dadosUsuario).addOnCanceledListener {
            mensagem(view, "Agendamento realizado, Obrigado pela preferência!", "#FF03DAC5")
        }.addOnFailureListener {
            mensagem(view, "Erro no servidor!", "#FF0000")
        }

    }
}