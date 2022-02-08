package org.viroth.bookstore.app.data.local
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class SharedPreferenceHelper {

    companion object {
        private var prefs: SharedPreferences? = null
        private var prefsHelper: SharedPreferenceHelper? = null

        @Synchronized
        fun getInstance(context: Context): SharedPreferenceHelper {
            if (prefsHelper == null) {
                prefsHelper = SharedPreferenceHelper()
                prefs = context.getSharedPreferences("bobGoPref", 0)
            }
            return prefsHelper!!
        }
    }

}