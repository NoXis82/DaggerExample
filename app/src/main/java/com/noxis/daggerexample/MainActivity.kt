package com.noxis.daggerexample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var drawerLayout: DrawerLayout? = null
    private var navigationView: NavigationView? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        init()
    }

    private fun init() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController!!, drawerLayout)
        NavigationUI.setupWithNavController(navigationView!!, navController!!)
        navigationView?.setNavigationItemSelectedListener(this)
    }

    private fun isValidDestination(destination: Int): Boolean {
        return destination != navController?.currentDestination?.id
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                sessionManager.logOut()
                return true
            }

            android.R.id.home -> {
                return if (drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
                    drawerLayout?.closeDrawer(GravityCompat.START)
                    true
                } else {
                    false
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.nav_profile -> {
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.main, true).build()
                navController?.navigate(R.id.profileScreen, null, navOptions)
            }

            R.id.nav_posts -> {
                if (isValidDestination(R.id.postsScreen)) {
                    navController?.navigate(R.id.postsScreen)
                }
            }
        }
        p0.isCheckable = true
        drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController!!, drawerLayout)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}