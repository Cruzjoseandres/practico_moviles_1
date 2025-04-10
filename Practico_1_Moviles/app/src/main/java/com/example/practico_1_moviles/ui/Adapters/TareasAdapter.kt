package com.example.practico_1_moviles.ui.Adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practico_1_moviles.databinding.TareaslistadapterBinding
import com.example.practico_1_moviles.ui.Adapters.TareasAdapter.ViewHolder
import com.example.practico_1_moviles.Models.TareasListas



class TareasAdapter(
    private var tareas: ArrayList<TareasListas>
) : RecyclerView.Adapter<ViewHolder>() {
    var tareasClickListener: TareasClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TareaslistadapterBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tareas[position]
        holder.bind(item, tareasClickListener)
    }

    override fun getItemCount(): Int {
        return tareas.size
    }

    fun setOnTareaClickListener(listener: TareasClickListener) {
        tareasClickListener = listener
    }

    fun setData(tareas: java.util.ArrayList<TareasListas>) {
        this.tareas = tareas
        notifyDataSetChanged()
    }




    class ViewHolder(private val binding: TareaslistadapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tarea: TareasListas, listener: TareasClickListener?) {

            binding.textTitle.text = tarea.titulo
            binding.textParrafo.text = tarea.descripcion
            binding.checkBox.isChecked = tarea.estado
            binding.list.setBackgroundColor(tarea.color)

            aplicarEstiloTachado(tarea.estado)

            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                tarea.estado = isChecked  //  Actualiza el modelo
                aplicarEstiloTachado(isChecked)  //  Cambia el estilo
            }

            binding.root.setOnClickListener {
                listener?.onTareaDetailClick(tarea)
            }
        }


        private fun aplicarEstiloTachado(tachado: Boolean) {
            binding.textTitle.paintFlags = if (tachado) {
                binding.textTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                binding.textTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

            binding.textParrafo.paintFlags = if (tachado) {
                binding.textParrafo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                binding.textParrafo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }


    interface TareasClickListener {
        fun onTareaClick(tarea: TareasListas)
        fun onTareaDetailClick(tarea: TareasListas)
    }
}




