package org.viroth.bookstore.app.networking.repository

import android.content.Context
import org.viroth.bookstore.app.networking.http.AppService
import org.viroth.bookstore.app.networking.http.ResultOf
import org.viroth.bookstore.app.networking.http.safeApiCall

class AppRepository(
    private val context: Context? = null,
    private val appService: AppService
) {

}