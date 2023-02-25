package com.appninjas.eventscalendartspc.presentation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.appninjas.eventscalendartspc.R
import com.appninjas.eventscalendartspc.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firebaseAuth = Firebase.auth
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth.currentUser ?: findNavController(R.id.nav_controller_fragment).navigate(R.id.registrationFragment)
        setupUI()
    }

    private fun setupUI() {
        val toolbar = binding.mainFragmentToolbar
        setSupportActionBar(toolbar)
        val drawer = binding.drawerLayout
        val navigationView = binding.mainFragmentNavMenu
        navigationView.setNavigationItemSelectedListener(navigationChangeListener)
        navigationView.setCheckedItem(R.id.nav_menu_btn)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_navigation, R.string.close_navigation)
        val drawable = DrawerArrowDrawable(applicationContext)
        drawable.color = Color.parseColor("#FFFFFF")
        toggle.drawerArrowDrawable = drawable
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private val navigationChangeListener =
        OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_menu_btn -> {
                    findNavController(R.id.nav_controller_fragment).navigate(R.id.mainFragment)
                    true
                }
                R.id.nav_profile -> {
                    findNavController(R.id.nav_controller_fragment).navigate(R.id.profileFragment)
                    true
                }
                R.id.admin_add_event -> {
                    findNavController(R.id.nav_controller_fragment).navigate(R.id.adminFragment)
                    true
                }
                else -> false
            }
        }

}