package com.practica1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.practica1.databinding.FragmentEcuationOneBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalcular.setOnClickListener {
            val radio = binding.numRadio.text.toString().toFloat()
            val altura = binding.numAltura.text.toString().toFloat()

            val volumen : Double = radio * 3.1416 * radio * altura
            binding.txtResultado.text = getString(R.string.volumen, volumen.toString())
            Toast.makeText(requireActivity(), "Numero $radio", Toast.LENGTH_LONG).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}