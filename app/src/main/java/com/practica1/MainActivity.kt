package com.practica1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practica1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bundle = intent.extras
        val volumen = bundle?.getString("volumen")
        val radio = bundle?.getString("radio")
        val altura = bundle?.getString("altura")

        binding.txtVol.text = getString(R.string.volumen_res, volumen)
        binding.txtAltura.text = getString(R.string.altura_res, altura)
        binding.txtRadio.text = getString(R.string.radio_res, radio)
    }
}