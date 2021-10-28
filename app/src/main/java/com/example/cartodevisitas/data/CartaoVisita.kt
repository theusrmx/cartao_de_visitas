package com.example.cartodevisitas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartaoVisita(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val telefone: String,
    val email: String,
    val empresa: String,
    val fundoPersonalizado: String
)