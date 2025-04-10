package com.example.practico_1_moviles.ui.MainActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practico_1_moviles.R
import com.example.practico_1_moviles.databinding.PrincipalMainBinding
import com.example.practico_1_moviles.ui.Adapters.TareasAdapter
import com.example.practico_1_moviles.Models.TareasListas
import com.example.practico_1_moviles.repositories.TareasRepository
import com.example.practico_1_moviles.ui.ViewModels.PrincipalViewModels

class Principal : AppCompatActivity(), TareasAdapter.TareasClickListener {
    private lateinit var binding: PrincipalMainBinding
    private val viewModel: PrincipalViewModels by viewModels()
    private lateinit var btnAdd: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = PrincipalMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnAdd = binding.btnAdd
        setupRecyclerView()
        setupEventListener()
        setupViewModelObeservers()
    }

    override fun onResume() {
        super.onResume()
        reloadData()
    }


    private fun reloadData() {
        val tareas = TareasRepository.getTareas()
        val adapter = binding.rvTarealist.adapter as TareasAdapter
        adapter.setData(tareas)
    }


    private fun setupViewModelObeservers() {
        viewModel.navegarMainActivity2.observe(this) { navegar ->
            if (navegar) {
                val intent = Intent(this, Form::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                viewModel.resetNavegacion()
            }
        }

    }

    private fun setupEventListener() {
        btnAdd.setOnClickListener {
            viewModel.onBotonAgregarClicked()
        }

    }

    private fun setupRecyclerView() {
        val adapter = TareasAdapter(arrayListOf())
        binding.rvTarealist.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@Principal).apply {
                orientation = RecyclerView.VERTICAL
            }
            addItemDecoration(
                DividerItemDecoration(
                    this@Principal,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        adapter.setOnTareaClickListener(this)
    }

    override fun onTareaClick(tarea: TareasListas) {

    }

    override fun onTareaDetailClick(tarea: TareasListas) {
        val intent = Form.detailIntent(this, tarea)
        startActivity(intent)
    }
}