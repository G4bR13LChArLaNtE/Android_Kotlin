package com.example.appdepizza

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appdepizza.databinding.ActivityProductDatailsBinding
import java.text.DecimalFormat

class ProductDatails : AppCompatActivity() {


    private lateinit var binding: ActivityProductDatailsBinding
    var amount = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDatailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        var imgProduct = intent.extras!!.getInt("imgProduct")
        var name = intent.extras!!.getString("name")
        var price = intent.extras!!.getString("price")!!.toDouble()
        var newPrice = price
        val decimalFormat = DecimalFormat.getCurrencyInstance()


        binding.imgProduct.setBackgroundResource(imgProduct)
        binding.txtProductName.text = "$name"
        binding.txtProductPrice.text = decimalFormat.format(price)

        binding.btBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btIncrease.setOnClickListener {
            if(amount == 1){
                binding.txtAmount.text = "2"
                newPrice += price
                amount = 2
            }else if(amount == 2){
            binding.txtAmount.text = "3"
            newPrice += price
            amount = 3
            }
            binding.txtProductPrice.text = decimalFormat.format(newPrice)
        }


        binding.btToDecrease.setOnClickListener {
            if(amount == 3){
                binding.txtAmount.text = "2"
                newPrice -= price
                amount = 2
            }else if(amount == 2){
                binding.txtAmount.text = "1"
                newPrice -= price
                amount = 1
            }
            binding.txtProductPrice.text = decimalFormat.format(newPrice)
        }


        binding.btConfirm.setOnClickListener {
            val mustard = binding.btMustard
            val ketchup = binding.btketchup
            val lemonSoda = binding.btLemonSoda
            val juice = binding.btJuice

            val saucesAndDrinks = when {
                mustard.isChecked -> {
                    "Mustard"
                }
                ketchup.isChecked -> {
                    "Ketchup"
                }
                lemonSoda.isChecked -> {
                    "Lemon Soda"
                }
                juice.isChecked -> {
                    "Juice"
                }
                else -> {
                    ""
                }
            }

            val intent = Intent(this, Payment::class.java)
            intent.putExtra("name",name)
            intent.putExtra("amount",amount)
            intent.putExtra("total",newPrice)
            intent.putExtra("saucesAndDrinks",saucesAndDrinks)
            startActivity(intent)

        }

    }


}