package br.com.mobile.infocountries

import android.app.Application

class InfocountriesApplication: Application() {

    // chamado quando android iniciar o processo da aplicação
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        // singleton
        private var appInstance: InfocountriesApplication?  = null
        fun getInstance(): InfocountriesApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no Android Manifest")
            }
            return appInstance!!
        }
    }

    // chamado quando android terminar processo da aplicação

    override fun onTerminate() {
        super.onTerminate()
    }
}