package com.practica1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.practica1.fragments.EcuationThree


class MainActivity2 : AppCompatActivity() {

    private lateinit var ecuationOne: EcuationOne
    private lateinit var ecuationTwo: EcuationTwo
    private lateinit var ecuationThree: EcuationThree
    private lateinit var transaction: FragmentTransaction
    private lateinit var spinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val transaction = supportFragmentManager.beginTransaction()
        spinner = findViewById(R.id.spinner_ecuation)

        ecuationOne = EcuationOne()
        ecuationTwo = EcuationTwo()
        ecuationThree = EcuationThree()

        transaction.add(R.id.main_frame, ecuationOne).commit()


        ArrayAdapter.createFromResource(
            this,
            R.array.ecuations,
            R.layout.spinner_select
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_items)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                when (pos){
                    0 ->{
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, ecuationOne)
                            .commit()
                    }
                    1 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, ecuationTwo)
                            .commit()
                    }
                    2 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, ecuationThree)
                            .commit()
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

    }
}