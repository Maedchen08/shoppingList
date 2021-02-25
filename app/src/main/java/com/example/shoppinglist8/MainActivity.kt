package com.example.shoppinglist8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shoppinglist8.additem.AddItemFragment
import com.example.shoppinglist8.itemlist.ListItemFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var addFragment = AddItemFragment.newInstance()
    private var listFragment = ListItemFragment.newInstance()
//    lateinit var navController: NavController
//    lateinit var navHostFragment: NavHostFragment
//    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.findNavController()
//        bottomNavigationView = findViewById(R.id.bottom_navigation)
//        bottomNavigationView.setupWithNavController(navController)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.addItemFragment-> replaceFragment(addFragment)
                R.id.listItemFragment-> replaceFragment(listFragment)

            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, fragment)
            transaction.commit()
        }
    }
}