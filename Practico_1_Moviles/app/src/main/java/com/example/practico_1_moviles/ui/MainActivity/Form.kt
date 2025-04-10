package com.example.practico_1_moviles.ui.MainActivity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practico_1_moviles.Models.TareasListas
import com.example.practico_1_moviles.R
import com.example.practico_1_moviles.databinding.FormMainBinding
import com.example.practico_1_moviles.ui.ViewModels.FormViewModels
import kotlin.getValue
import androidx.core.graphics.toColorInt

class Form : AppCompatActivity() {
    private var tarea: TareasListas? = null
    private lateinit var editTextTitle: EditText
    private lateinit var editTextTextDescripcion: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancelar: Button
    private lateinit var btnDelete: Button

    private lateinit var btnRojo: Button
    private lateinit var btnVerde: Button
    private lateinit var btnAmarillo: Button
    private lateinit var btnAzul: Button
    private lateinit var btnMorado: Button


    lateinit var binding: FormMainBinding
    private val viewModel: FormViewModels by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = FormMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tarea = intent.getSerializableExtra(PARAM_TAREA) as TareasListas?
        editTextTitle = binding.editTextTitle
        editTextTextDescripcion = binding.editTextTextDescripcion
        btnSave = binding.btnSave
        btnCancelar = binding.btnCancelar
        btnDelete = binding.btnDelete

        btnRojo = binding.btnRojo
        btnVerde = binding.btnVerde
        btnAmarillo = binding.btnAmarillo
        btnAzul = binding.btnAzul
        btnMorado = binding.btnMorado

        viewModel.loadTareasDetail(tarea)

        setupEventlister()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.navegarMainActivity.observe(this) { navegar ->
            if (navegar) {
                val intent = Intent(this, Principal::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
                viewModel.resetNavegacion()
            }
        }

        viewModel.tareaDetail.observe(this) { tarea ->
            if (tarea != null) {
                binding.editTextTitle.setText(tarea.titulo)
                binding.editTextTextDescripcion.setText(tarea.descripcion)
            }
        }
    }



    private fun setupEventlister() {

        btnSave.setOnClickListener {
            val titulo = editTextTitle.text.toString()
            val descripcion = editTextTextDescripcion.text.toString()
            viewModel.save(tarea, titulo, descripcion)
            viewModel.onBotonAgregarClicked()
            finish()
        }

        btnCancelar.setOnClickListener {
            finish()
        }

        btnDelete.setOnClickListener {
            viewModel.deleteTarea(tarea)
            finish()
        }




        btnRojo.setOnClickListener { viewModel.setColorButton("#FF0000".toColorInt()) }
        btnVerde.setOnClickListener {  viewModel.setColorButton("#00FF00".toColorInt()) }
        btnAmarillo.setOnClickListener {  viewModel.setColorButton("#FFFF00".toColorInt()) }
        btnAzul.setOnClickListener {  viewModel.setColorButton("#0000FF".toColorInt()) }
        btnMorado.setOnClickListener {  viewModel.setColorButton("#800080".toColorInt()) }
    }


    companion object {
        const val PARAM_TAREA = "tarea"
        fun detailIntent(context: Context, tarea: TareasListas): Intent? {
            val intent = Intent(context, Form::class.java)
            intent.putExtra(PARAM_TAREA, tarea)
            return intent
        }
    }

}