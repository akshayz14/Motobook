package com.apc.motobook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import com.apc.motobook.R
import com.apc.motobook.view.fragments.HomeFragment
import com.apc.motobook.view.fragments.SplashFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

    }
}