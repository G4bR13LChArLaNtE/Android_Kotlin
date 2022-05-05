package br.com.mobile.infocountries

import android.content.Context



object Paisservice {

    fun getPaises(context: Context): List<Pais> {
        val paises = mutableListOf<Pais>()

        // criar 9 países


        //Pais 1
        val p1 = Pais()
        p1.nome_pais = "Brasil"
        p1.bandeira = "https://img.icons8.com/color/48/000000/brazil.png"
        p1.capital = "Brasilia"
        p1.continente = "América"
        p1.populacao = "212,6 milhões"
        p1.latitude = "-14.2350"
        p1.longitude = "-51.9253"
        paises.add(p1)


         //Pais 2
         val p2 = Pais()
         p2.nome_pais ="EUA"
         p2.bandeira = "https://img.icons8.com/color/48/000000/usa.png"
         p2.capital = "Washington"
         p2.continente = "América"
         p2.populacao = "329,5 milhões"
         p2.latitude = "37.0902"
         p2.longitude = "-95.7129"
         paises.add(p2)


        //Pais 3
        val p3 = Pais()
        p3.nome_pais = "Argentina"
        p3.bandeira = "https://img.icons8.com/color/48/000000/argentina.png"
        p3.capital = "Buenos Aires"
        p3.continente = "América"
        p3.populacao = "45,38 milhões"
        p3.latitude = "-34.6083"
        p3.longitude = "-58.3712"
        paises.add(p3)


        //Pais 4
        val p4 = Pais()
        p4.nome_pais = "França"
        p4.bandeira = "https://img.icons8.com/color/48/000000/france.png"
        p4.capital = "Paris"
        p4.continente = "Europa"
        p4.populacao = "67,39 milhões"
        p4.latitude = "-20.5418"
        p4.longitude = "-47.4197"
        paises.add(p4)


        //Pais 5
        val p5 = Pais()
        p5.nome_pais = "Alemanha"
        p5.bandeira = "https://img.icons8.com/color/48/000000/germany.png"
        p5.capital = "Berlim"
        p5.continente = "Europa"
        p5.populacao = "83,24 milhões"
        p5.latitude = "51.1657"
        p5.longitude = "10.4515"
        paises.add(p5)


       //Pais 6
       val p6 = Pais()
       p6.nome_pais = "Espanha"
       p6.bandeira = "https://img.icons8.com/color/48/000000/spain.png"
       p6.capital = "Madrid"
       p6.continente = "Europa"
       p6.populacao = "47,35 milhões"
       p6.latitude = "40.4167"
       p6.longitude = "-3.70325"
       paises.add(p6)

       //Pais 7
       val p7 = Pais()
       p7.nome_pais = "Inglaterra"
       p7.bandeira = "https://img.icons8.com/color/48/000000/england.png"
       p7.capital = "Londres"
       p7.continente = "Europa"
       p7.populacao = "55,98 milhões"
       p7.latitude = "51.5072"
       p7.longitude = "-0.1275"
       paises.add(p7)


       //Pais 8
       val p8 = Pais()
       p8.nome_pais = "Rússia"
       p8.bandeira = "https://img.icons8.com/color/48/000000/russian-federation.png"
       p8.capital = "Moscou"
       p8.continente = "Europa, Ásia"
       p8.populacao = "144,1 milhões"
       p8.latitude = "55.7508"
       p8.longitude = "37.6172"
       paises.add(p8)


       //Pais 9
       val p9 = Pais()
       p9.nome_pais = "Irlanda"
       p9.bandeira = "https://img.icons8.com/color/48/000000/ireland.png"
       p9.capital = "Dublin"
       p9.continente = "Europa"
       p9.populacao =  "4,995 milhões"
       p9.latitude = "53.3434"
       p9.longitude = "-6.26761"
       paises.add(p9)



        return paises
    }
}