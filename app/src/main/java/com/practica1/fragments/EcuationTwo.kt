package com.practica1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.practica1.databinding.FragmentEcuationTwoBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.sqrt

class EcuationTwo : Fragment() {

    private var _binding: FragmentEcuationTwoBinding? = null
    private val binding get () = _binding!!
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEcuationTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = binding.spinnerElement
        val context: Context? = getContext()
        var elementCalcular : String = "a"

        if (context != null) {
            ArrayAdapter.createFromResource(
                context,
                R.array.triangulo,
                R.layout.spinner_select
            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.spinner_items)
                spinner.adapter = adapter
            }
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                when(pos) {
                    0 -> {
                        elementCalcular = "a"
                        binding.txtElemento1.text = getString(R.string.catetob)
                        binding.txtElemento2.text = getString(R.string.hipotenusa)
                    }
                    1 -> {
                        elementCalcular = "b"
                        binding.txtElemento1.text = getString(R.string.catetoa)
                        binding.txtElemento2.text = getString(R.string.hipotenusa)
                    }
                    2 -> {
                        elementCalcular = "c"
                        binding.txtElemento1.text = getString(R.string.catetoa)
                        binding.txtElemento2.text = getString(R.string.catetob)
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        binding.btnCalcular.setOnClickListener {
            val element1 = binding.numElemento1.text.toString()
            val element2 = binding.numElemento2.text.toString()

            when {
                element1.isEmpty() -> {
                    binding.numElemento1.error = getString(R.string.requerido)
                }
                element2.isEmpty() -> {
                    binding.numElemento2.error  = getString(R.string.requerido)
                }
                else -> {
                    when(elementCalcular){
                        "a" -> {
                            if(element1.toFloat() >= element2.toFloat())
                                Toast.makeText(requireActivity(),getString(R.string.hip_iguales),Toast.LENGTH_LONG).show()
                            else{
                                resolveEcuation(element1.toFloat(),element2.toFloat(),elementCalcular)
                            }

                        }
                        "b" -> {
                            if(element1.toFloat() >= element2.toFloat())
                                Toast.makeText(requireActivity(),getString(R.string.hip_iguales),Toast.LENGTH_LONG).show()
                            else{
                                resolveEcuation(element1.toFloat(),element2.toFloat(),elementCalcular)
                            }
                        }
                        "c" -> {
                            resolveEcuation(element1.toFloat(),element2.toFloat(),elementCalcular)
                        }
                    }
                }
            }
        }
    }

    fun resolveEcuation(element1: Float, element2: Float, lado: String) {
        if (lado == "c"){
            val hip: Double = sqrt(Math.pow(element2.toDouble(),2.toDouble())+Math.pow(element1.toDouble(),2.toDouble()))
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN
            val roundHip= df.format(hip).toString()
            binding.txtResultado.text = getString(R.string.hip_res,roundHip)
        }
        else {
            val cateto: Double = sqrt(Math.pow(element2.toDouble(),2.toDouble())-Math.pow(element1.toDouble(),2.toDouble()))
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN
            val roundCateto= df.format(cateto).toString()
            binding.txtResultado.text = getString(R.string.cateto_res,lado,roundCateto)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}