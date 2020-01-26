package com.willrussell.manmethacks2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openRecordings(v: View?) {
        val intent = Intent(applicationContext, LibraryActivity::class.java)
        startActivity(intent)
    }
}
