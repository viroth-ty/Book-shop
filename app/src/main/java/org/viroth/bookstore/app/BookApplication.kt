package org.viroth.bookstore.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.viroth.bookstore.app.module.appModule
import org.viroth.bookstore.app.networking.http.appService
import org.viroth.bookstore.app.networking.repository.AppRepository

object MyKoinContext {
    var koinApp : KoinApplication? = null
}

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

        startKoin {
            androidLogger(level = Level.DEBUG)
            modules(appModule)
            androidContext(this@BookApplication)
            androidFileProperties(koinPropertyFile = "book.properties")
        }

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        context = this
        appRepository = AppRepository(appService = appService)
    }

}