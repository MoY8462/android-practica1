package com.practica1

import android.content.Intent
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
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEcuationOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalcular.setOnClickListener {
            val radio = binding.numRadio.text.toString()
            val altura = binding.numAltura.text.toString()

            when {
                radio.isEmpty() -> {
                    binding.numRadio.error = getString(R.string.requerido)
                }
                altura.isEmpty() -> {
                    binding.numAltura.error  = getString(R.string.requerido)
                }
                else -> {
                    val volumen : Double = radio.toFloat()* altura.toFloat() * 3.1416 * radio.toFloat()
                    val df = DecimalFormat("#.##")
                    df.roundingMode = RoundingMode.DOWN
                    val roundVolumen = df.format(volumen).toString()
                    binding.txtResultado.text =  getString(R.string.volumen, roundVolumen)
                    Toast.makeText(requireActivity(), getString(R.string.volumen, roundVolumen), Toast.LENGTH_LONG).show()

                    var intent = Intent(this.context, MainActivity::class.java).apply{
                        putExtra("volumen", roundVolumen)
                        putExtra("altura",altura)
                        putExtra("radio",radio)
                    }
                    startActivity(intent)
                }

            }

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

