package com.example.practica_guiada_1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practica_guiada_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Declaracion del ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el diseño XML mediante ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Evento Click: Evaluar Nota
        binding.btnEvaluar.setOnClickListener {
            evaluarEstudiante()
        }

        // Evento Click: Navegar a DetailActivity
        binding.btnNavegar.setOnClickListener {
            navegarADetalle()
        }
    }

    private var nota_final_dec: String = ""
    private var estado: String = ""

    private fun evaluarEstudiante() {
        // Sintaxis Kotlin: Declaración de variables inmutables (val)
        val nombre = binding.etNombre.text.toString().trim()
        val apellido = binding.etApellido.text.toString().trim()
        val nota1Texto = binding.etNota1.text.toString().trim()
        val nota2Texto = binding.etNota2.text.toString().trim()
        val nota3Texto = binding.etNota3.text.toString().trim()
        val tvResultado = binding.tvResultado.toString().trim()

        // Control de flujo: Estructura condicional if / else
        if (nombre.isEmpty() || nota1Texto.isEmpty() || nota2Texto.isEmpty() || nota3Texto.isEmpty()) {
            Toast.makeText( this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        //Conversión segura de tipos de datos
        val nota1 = nota1Texto.toDoubleOrNull()
        val nota2 = nota2Texto.toDoubleOrNull()
        val nota3 = nota3Texto.toDoubleOrNull()

        if (nota1 == null || nota1 !in 0.0 .. 20.00) {
            binding.tvResultado.text = "Error: Ingrese una nota valida entre 0 y 20"
            return
        }
        if (nota2 == null || nota2 !in 0.0 .. 20.00) {
            binding.tvResultado.text = "Error: Ingrese una nota valida entre 0 y 20"
            return
        }
        if (nota3 == null || nota3 !in 0.0 .. 20.00) {
            binding.tvResultado.text = "Error: Ingrese una nota valida entre 0 y 20"
            return
        }

        //Estructura condicional para determinar estado del estudiante

        val nota_final = (nota1+nota2+nota3) / 3

        if (nota_final>= 18){
            estado = "Excelente  Promedio"
            binding.tvResultado.setTextColor(Color.BLUE)
        } else if (nota_final >= 10.5){
            estado = "Buen Promedio"
            binding.tvResultado.setTextColor(Color.GREEN)
        } else {
            estado = "Hay que esforzarse para mejorar ese promedio"
            binding.tvResultado.setTextColor(Color.RED)
        }

        nota_final_dec = String.format("%.1f",nota_final)

        //Interpolación de String ($nombre, $nota, $estado)
        binding.tvResultado.text = "Estudiante: $nombre $apellido\nNota: $nota_final_dec\nEstado: $estado"
    }

    private fun navegarADetalle() {
        val nombre = binding.etNombre.text.toString().trim()
        val apellido = binding.etApellido.text.toString().trim()

        if (nombre.isEmpty()){
            Toast.makeText(this, "Escriba un nombre para enviar", Toast.LENGTH_SHORT).show()
            return
        }

        // Creación de un Intent explicito para cambiar de pantalla
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra( "EXTRA_NOMBRE", nombre)
            putExtra("EXTRA_APELLIDO", apellido)
            putExtra("EXTRA_NOTA", nota_final_dec)
            putExtra("EXTRA_ESTADO", estado)
        }
        startActivity(intent)
    }
}