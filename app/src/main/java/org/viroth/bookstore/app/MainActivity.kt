package org.viroth.bookstore.app

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import org.viroth.bookstore.app.databinding.ActivityMainBinding
import org.viroth.bookstore.app.util.KeepStateNavigator

class MainActivity : BookAppActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navigator = KeepStateNavigator(this,
            navHostFragment.childFragmentManager,
            R.id.nav_host_fragment
        )
        navController.navigatorProvider += navigator
        navController.setGraph(R.navigation.app_navigation)

        initBottomNavigationView(navController = navController)

    }

    private fun initBottomNavigationView(navController: NavController) {
        binding.bottomNavigationView.setupWithNavController(navController = navController)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            try {
                when (item.itemId) {
                    R.id.bookContainerFragment -> {
                        Navigation.findNavController(this, R.id.book_host_fragment).navigateUp()
                    }
                }
            } catch (e: Exception) {

            }
            NavigationUI.onNavDestinationSelected(
                item,
                Navigation.findNavController(this, R.id.nav_host_fragment)
            )
        }
    }

}