package com.practica1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction


class MainActivity2 : AppCompatActivity() {

    private lateinit var ecuationOne: EcuationOne
    private lateinit var ecuationTwo: EcuationTwo
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val spinner : Spinner = findViewById(R.id.spinner_ecuation)

        ecuationOne = EcuationOne()
        ecuationTwo = EcuationTwo()

        supportFragmentManager.beginTransaction().add(R.id.main_frame, ecuationOne).commit()

        val lista = listOf("Ecuacion 1", "Ecuacion 2")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)

        spinner.adapter = adaptador

        spinner.onItemSelectedListener= object: AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                if(pos == 0) {
                    supportFragmentManager.beginTransaction().replace(R.id.main_frame, ecuationOne).commit()
                }
                if(pos == 1) {
                    supportFragmentManager.beginTransaction().replace(R.id.main_frame, ecuationTwo).commit()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

    }




}
