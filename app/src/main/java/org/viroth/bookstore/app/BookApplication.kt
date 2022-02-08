package org.viroth.bookstore.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class BookApplication : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set

//        @SuppressLint("StaticFieldLeak")
//        lateinit var appRepository: AppRepository

    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        context = this

    }

}