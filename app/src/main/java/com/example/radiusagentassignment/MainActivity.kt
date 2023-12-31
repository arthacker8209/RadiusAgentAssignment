package com.example.radiusagentassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.radiusagentassignment.ui.FacilitiesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = FacilitiesFragment.newInstance()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fvMain, fragment)
        fragmentTransaction.commit()
    }
}