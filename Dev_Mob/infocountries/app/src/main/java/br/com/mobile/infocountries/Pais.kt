package br.com.mobile.infocountries

import java.io.Serializable

class Pais : Serializable {
    var id:Long = 0
    var nome_pais= ""
    var capital= ""
    var bandeira= ""
    var continente= ""
    var populacao=""
    var latitude=""
    var longitude=""

    override fun toString(): String {
        return "Pais(nome='$nome_pais')" +
                "Pais(bandeira= '$bandeira')" +
                "Pais(capital='$capital') "
    }
}