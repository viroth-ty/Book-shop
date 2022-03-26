package org.viroth.bookstore.app

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.epoxy.Carousel
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.scope.activityRetainedScope
import org.koin.androidx.scope.activityScope
import org.koin.core.context.KoinContext
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.viroth.bookstore.app.module.Classes
import org.viroth.bookstore.app.module.Student
import org.viroth.bookstore.app.module.UserSession

open class BookAppActivity : AppCompatActivity(), AndroidScopeComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Carousel.setDefaultGlobalSnapHelperFactory(null)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT


        reuseSession()
    }
    private fun reuseSession(){
        val ourSession = getKoin().createScope("ourSession", named("Session"))
        scope.linkTo(ourSession)
        val userSession = get<UserSession>()
        userSession.startSession(userName = "Viroth ty")
        userSession.endSession()

    }

    override val scope: Scope by activityRetainedScope()

}