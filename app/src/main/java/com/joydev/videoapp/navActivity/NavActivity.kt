package com.joydev.videoapp.navActivity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.joydev.videoapp.R

class NavActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var viewModel: NavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        viewModelInit()
    }

    private fun viewModelInit() {
        viewModel = ViewModelProviders.of(this).get(NavViewModel::class.java)
    }
}
