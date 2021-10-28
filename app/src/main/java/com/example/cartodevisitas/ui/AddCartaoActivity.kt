package com.example.cartodevisitas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cartodevisitas.App
import com.example.cartodevisitas.R
import com.example.cartodevisitas.data.CartaoVisita
import com.example.cartodevisitas.databinding.ActivityAddCartaoBinding
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape

class AddCartaoActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityAddCartaoBinding.inflate(layoutInflater)} //atalho para acessar as views

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    lateinit var corSelecionada: String
    private fun insertListeners(){
        binding.btnClose.setOnClickListener{
            finish()
        }

        binding.tilCor.setOnClickListener {
            val corPadrao = R.color.white
            ColorPickerDialog
                .Builder(this)        				// Pass Activity Instance
                .setTitle("Selecione uma cor")           	// Default "Choose Color"
                .setColorShape(ColorShape.CIRCLE)   // Default ColorShape.CIRCLE
                .setDefaultColor(corPadrao)     // Pass Default Color
                .setColorListener { color, colorHex ->
                    corSelecionada = colorHex
                }
                .show()

        }

        binding.btnSalvar.setOnClickListener{
          val cartaoVisita = CartaoVisita(
              nome = binding.tilNome.editText?.text.toString(),
              telefone= binding.tilTelefone.editText?.text.toString(),
              email = binding.tilEmail.editText?.text.toString(),
              empresa = binding.tilEmpresa.editText?.text.toString(),
              fundoPersonalizado = corSelecionada

          )
            mainViewModel.insert(cartaoVisita)
            Toast.makeText(this, R.string.label_show_sucess, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}