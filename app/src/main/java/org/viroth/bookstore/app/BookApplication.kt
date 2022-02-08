package org.viroth.bookstore.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import org.viroth.bookstore.app.networking.http.appService
import org.viroth.bookstore.app.networking.repository.AppRepository

class BookApplication : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set

        @SuppressLint("StaticFieldLeak")
        lateinit var appRepository: AppRepository

    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        context = this
        appRepository = AppRepository(context = this, appService = appService)

    }

}