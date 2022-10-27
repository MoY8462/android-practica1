package com.practica1.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.practica1.MainActivity3
import com.practica1.MainActivity5
import com.practica1.R
import com.practica1.databinding.FragmentEcuationThreeBinding
import java.math.RoundingMode
import java.text.DecimalFormat


class EcuationThree : Fragment() {

    private var _binding: FragmentEcuationThreeBinding? = null
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
        _binding = FragmentEcuationThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = binding.spinnerElement
        val context: Context? = getContext()
        var elementCalcular : String = "p"

        if (context != null) {
            ArrayAdapter.createFromResource(
                context,
                R.array.gases,
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
                        elementCalcular = "p"
                        binding.numPresion.setBackgroundColor(resources.getColor(R.color.slate_200))
                        binding.numVolumen.setBackground(resources.getDrawable(R.drawable.style_border))
                        binding.numMol.setBackground(resources.getDrawable(R.drawable.style_border))
                        binding.numTemperatura.setBackground(resources.getDrawable(R.drawable.style_border))

                        disabledElemetns (false, true, true, true)
                    }
                    1 -> {
                        elementCalcular = "v"
                        binding.numPresion.setBackground(resources.getDrawable(R.drawable.style_border))
                        binding.numVolumen.setBackgroundColor(resources.getColor(R.color.slate_200))
                        binding.numMol.setBackground(resources.getDrawable(R.drawable.style_border))
                        binding.numTemperatura.setBackground(resources.getDrawable(R.drawable.style_border))
                        disabledElemetns (true, false, true, true)
                    }
                    2 -> {
                        elementCalcular = "n"
                        binding.numPresion.setBackground(resources.getDrawable(R.drawable.style_border))
                        binding.numVolumen.setBackground(resources.getDrawable(R.drawable.style_border))
                        binding.numMol.setBackgroundColor(resources.getColor(R.color.slate_200))
                        binding.numTemperatura.setBackground(resources.getDrawable(R.drawable.style_border))
                        disabledElemetns (true, true, true, false)
                    }
                    3 -> {
                        elementCalcular = "t"
                        binding.numPresion.setBackground(resources.getDrawable(R.drawable.style_border))
                        binding.numVolumen.setBackground(resources.getDrawable(R.drawable.style_border))
                        binding.numMol.setBackground(resources.getDrawable(R.drawable.style_border))
                        binding.numTemperatura.setBackgroundColor(resources.getColor(R.color.slate_200))
                        disabledElemetns (true, true, false, true)
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        binding.btnCalcular.setOnClickListener {

            val presion = binding.numPresion.text.toString()
            val volumen = binding.numVolumen.text.toString()
            val temperatura = binding.numTemperatura.text.toString()
            val mol = binding.numMol.text.toString()


            if (presion.isEmpty() and (elementCalcular != "p")) {
                binding.numPresion.error = getString(R.string.requerido)
            }
            else if (volumen.isEmpty()  and (elementCalcular != "v")) {
                binding.numVolumen.error  = getString(R.string.requerido)
            }
            else if(mol.isEmpty() and (elementCalcular != "n")) {
                binding.numMol.error = getString(R.string.requerido)
            }
            else if(temperatura.isEmpty() and (elementCalcular != "t")){
                binding.numTemperatura.error  = getString(R.string.requerido)
            }

            //else if (presion.toFloat() < 0) binding.numPresion.error = "Valor erroneo"
            //else if (volumen.toFloat() < 0) binding.numVolumen.error  = "Valor erroneo"
            //else if (molNum.toFloat() < 0)  binding.numMol.error  = "Valor erroneo"
            else  {
                when(elementCalcular){
                    "p" -> {
                        val presion_res = (mol.toFloat()*0.082*temperatura.toFloat())/volumen.toFloat()
                        val df = DecimalFormat("#.###")
                        df.roundingMode = RoundingMode.DOWN
                        val roundPres= df.format(presion_res).toString()
                        binding.txtResultado.text = getString(R.string.presion_res,roundPres)
                        sendData (roundPres, volumen.toString(), temperatura.toString(), mol.toString())
                    }
                    "v" -> {
                        val vol_res = (mol.toFloat()*0.082*temperatura.toFloat())/presion.toFloat()
                        val df = DecimalFormat("#.###")
                        df.roundingMode = RoundingMode.DOWN
                        val roundVol= df.format(vol_res).toString()
                        binding.txtResultado.text = getString(R.string.vol_res,roundVol)
                        sendData (presion.toString(), roundVol, temperatura.toString(), mol.toString())
                    }
                    "n" -> {
                        val mol_res = (presion.toFloat()*volumen.toFloat())/(0.082*temperatura.toFloat())
                        val df = DecimalFormat("#.###")
                        df.roundingMode = RoundingMode.DOWN
                        val roundMol= df.format(mol_res).toString()
                        binding.txtResultado.text = getString(R.string.mol_res,roundMol)
                        sendData (presion.toString(), volumen.toString(), temperatura.toString(), roundMol)
                    }
                    "t" -> {
                        val temp_res = (presion.toFloat()*volumen.toFloat())/(0.082*mol.toFloat())
                        val df = DecimalFormat("#.###")
                        df.roundingMode = RoundingMode.DOWN
                        val roundTemp= df.format(temp_res).toString()
                        binding.txtResultado.text = getString(R.string.temp_res,roundTemp)
                        sendData (presion.toString(), volumen.toString(), roundTemp, mol.toString())
                    }
                }
            }

        }
    }
    fun sendData (presion: String, volumen: String, temperatura: String, mol:String){
        val intent = Intent(this.context, MainActivity5::class.java).apply{
            putExtra("presion", presion)
            putExtra("volumen", volumen)
            putExtra("mol", mol)
            putExtra("temperatura", temperatura)
        }
        startActivity(intent)
    }
    fun disabledElemetns (presion: Boolean, volumen: Boolean, temperatura: Boolean, mol:Boolean) {
        binding.numPresion.isEnabled = presion
        binding.numVolumen.isEnabled = volumen
        binding.numTemperatura.isEnabled = temperatura
        binding.numMol.isEnabled = mol
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}