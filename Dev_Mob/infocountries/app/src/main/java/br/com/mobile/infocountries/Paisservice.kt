package br.com.mobile.infocountries

import android.content.Context
import android.util.Log
import android.view.ViewConfiguration.get
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken




object Paisservice {

    val host = "http://192.168.15.15:5000"
    val TAG = "WS_infocoutries"

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

    fun getPaises(context: Context): List<Pais> {
            val url = "$host/paises"
            val json = HttpHelper.get(url)
            return parserJson<List<Pais>>(json)
        }


    fun save(pais: Pais): Response {
        val json = HttpHelper.post("$host/paises/${pais.nome_pais}", "")
        return parserJson<Response>(json)
    }

    fun delete(pais: Pais): Response {
        Log.d(TAG, pais.nome_pais.toString())
        val url = "$host/paises/${pais.nome_pais}"
        val json = HttpHelper.delete(url)
        Log.d(TAG, json)
        return parserJson(json)
    }
}
