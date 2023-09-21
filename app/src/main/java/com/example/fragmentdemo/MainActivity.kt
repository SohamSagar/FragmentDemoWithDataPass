package com.example.fragmentdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private var navigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView = findViewById(R.id.navigation)
        navigationView!!.setOnItemSelectedListener(selectedListener)

        replaceFragment("Home",HomeFragment())
    }

    private val selectedListener = NavigationBarView.OnItemSelectedListener{
        when (it.itemId) {
            R.id.nav_home -> {
                replaceFragment(title = "Home", fragment = HomeFragment())
                return@OnItemSelectedListener true
            }
            R.id.nav_cart -> {
                replaceFragment(title = "Cart", fragment = CartFragment())
                return@OnItemSelectedListener true
            }
            R.id.nav_profile -> {
                replaceFragment(title = "Profile", fragment = ProfileFragment())
                return@OnItemSelectedListener true
            }
        }
        return@OnItemSelectedListener false
    }

    private fun replaceFragment(title: String? = null, fragment: Fragment) {
        if (title!=null) {
            val bundle = Bundle()
            bundle.putString("title",title)
            fragment.arguments = bundle
        }
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl, fragment)
        fragmentTransaction.addToBackStack(fragment.toString())
        fragmentTransaction.commit()
    }

    fun removeFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(fragment)
        fragmentTransaction.commit()
        fragmentManager.popBackStack()
    }
}