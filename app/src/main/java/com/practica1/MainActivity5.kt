package com.practica1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.practica1.databinding.ActivityMainBinding

class MainActivity5 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        var bundle = intent.extras
        val presion = bundle?.getString("presion")
        val volumen = bundle?.getString("volumen")
        val mol = bundle?.getString("mol")
        val temp = bundle?.getString("temperatura")

        val pressText: TextView = findViewById(R.id.txtPresion)
        val volText: TextView = findViewById(R.id.txtVolumen)
        val molText: TextView = findViewById(R.id.txtMol)
        val rText: TextView = findViewById(R.id.txtR)
        val tempText: TextView = findViewById(R.id.txtTemp)
        pressText.setText(getString(R.string.presion) + " $presion")
        volText.setText(getString(R.string.volumen3) + ": $volumen")
        molText.setText(getString(R.string.mol)+ " $mol")
        rText.setText("R = 0.08206 [L atm]/[mol K]")
        tempText.setText(getString(R.string.temperatura) + " $temp")
    }
}