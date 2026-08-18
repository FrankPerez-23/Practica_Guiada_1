package com.example.practica_guiada_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practica_guiada_1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obtener el dato pasado desde MainActivity
        val nombreRecibido = intent.getStringExtra("EXTRA_NOMBRE") ?: "Invitado"

        binding.tvTituloDetalle.text = "¡Bienvenido a la pantalla de detalle, $nombreRecibido"

}