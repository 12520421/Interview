package com.example.demointerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.demointerview.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        println("haha : ${solution(10, 5, 6, 4, 8)}")
        println("haha : ${solution(10, 5, 6, 4, 9)}")
        println("haha : ${solution(5, 3, 7, 4, 6)}")
    }

    // solution for test 1
    private fun solution(value1: Int, weight1: Int, value2: Int, weight2: Int, maxW: Int): Int {
        val totalWeight = weight1 + weight2

        if (totalWeight <= maxW)
            return value1 + value2

        if (weight1 <= maxW && weight2 <= maxW)
            return if (value1 > value2) value1 else value2

        if (weight1 <= maxW)
            return value1

        if (value2 <= maxW)
            return value2

        return 0
    }


}