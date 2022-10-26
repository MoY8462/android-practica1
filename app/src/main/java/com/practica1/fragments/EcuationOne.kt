package com.practica1

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.practica1.databinding.FragmentEcuationOneBinding
import java.math.RoundingMode
import java.text.DecimalFormat

class EcuationOne : Fragment() {

    private var _binding : FragmentEcuationOneBinding? = null
    private val binding get () = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEcuationOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("StringFormatMatches")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalcular.setOnClickListener {
            val radio = binding.numRadio.text.toString()
            val altura = binding.numAltura.text.toString()

            when {
                radio.isEmpty() -> {
                    //Toast.makeText(requireActivity(), "Ingresa el radio del cilindro", Toast.LENGTH_SHORT).show()
                    binding.numRadio.error = "Ingresa el valor del radio"
                }
                altura.isEmpty() -> {
                    //Toast.makeText(requireActivity(), "Ingresa la altura del cilindro", Toast.LENGTH_SHORT).show()
                    binding.numAltura.error  = "Ingrsa el valor de la altura"
                }
                else -> {
                    val volumen : Double = radio.toFloat()* altura.toFloat() * 3.1416 * radio.toFloat()
                    val df = DecimalFormat("#.##")
                    df.roundingMode = RoundingMode.DOWN
                    val roundVolumen = df.format(volumen)
                    binding.txtResultado.text = "V = ${roundVolumen.toString()} cm³" //getString(R.string.volumen,volumen)
                    Toast.makeText(requireActivity(), "V = ${volumen.toString()} cm³", Toast.LENGTH_LONG).show()
                }

            }

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

