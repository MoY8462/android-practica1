package com.practica1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.practica1.databinding.ActivityMainBinding

class MainActivity3 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        var bundle = intent.extras
        val a = bundle?.getString("a")
        val b= bundle?.getString("b")
        val c = bundle?.getString("c")

        val aText: TextView = findViewById(R.id.txtA)
        val bText: TextView = findViewById(R.id.txtB)
        val cText: TextView = findViewById(R.id.txtC)
        aText.setText(getString(R.string.a_res, a))
        bText.setText(getString(R.string.b_res, b))
        cText.setText(getString(R.string.c_res, c))
    }
}