package com.example.cartodevisitas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.cartodevisitas.App
import com.example.cartodevisitas.databinding.ActivityMainBinding
import com.example.cartodevisitas.util.Image

class MainActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)} //atalho para acessar as views

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { CartaoVisitaAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvMain.adapter = adapter
        getAllCards()
        insertListeners()
    }

    private fun insertListeners(){
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddCartaoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getAllCards(){
        mainViewModel.getAll().observe(this,{ cartaoVisita ->
            adapter.submitList(cartaoVisita)
        })

        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)

        }
    }


}