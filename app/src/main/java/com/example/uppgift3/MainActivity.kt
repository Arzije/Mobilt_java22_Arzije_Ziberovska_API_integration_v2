package com.example.uppgift3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_host_fragment)

        if (navController.currentDestination?.id == R.id.startFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }


}
