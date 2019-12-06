package io.garage.catsticker

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}