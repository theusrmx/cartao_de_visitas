package com.example.cartodevisitas

import android.app.Application
import com.example.cartodevisitas.data.AppDatabase
import com.example.cartodevisitas.data.CartaoVisitaRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { CartaoVisitaRepository(database.cartaoDao()) }
}