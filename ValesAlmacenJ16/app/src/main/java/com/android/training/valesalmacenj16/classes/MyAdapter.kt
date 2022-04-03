package com.android.training.valesalmacenj16.classes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.training.valesalmacenj16.R

internal class MyAdapter (private val context: Context, private val list: ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvClaveLista : TextView
        var tvPresentacionLista : TextView
        var tvDescripcionLista : TextView
        var tvCantidadLista : TextView
        var tvCaducidadLista : TextView
        var tvRemisionLista : TextView
        var tvProcedenciaLista : TextView
        var tvLoteLista : TextView

        var textView : TextView

        init {
            tvClaveLista = itemView.findViewById(R.id.textViewClaveLista)
            tvPresentacionLista = itemView.findViewById(R.id.textViewPresentacionLista)
            tvDescripcionLista = itemView.findViewById(R.id.textViewDescripcionLista)
            tvCantidadLista = itemView.findViewById(R.id.textViewCantidadLista)
            tvLoteLista = itemView.findViewById(R.id.textViewLoteLista)
            tvCaducidadLista = itemView.findViewById(R.id.textViewCaducidadLista)
            tvRemisionLista = itemView.findViewById(R.id.textViewRemisionLista)
            tvProcedenciaLista = itemView.findViewById(R.id.textViewProcLista)

            textView = itemView.findViewById(R.id.textViewClaveLista) // Initialize your All views prensent in list items
        }

        fun bind(position: Int) {
            // This method will be called anytime a list item is created or update its data
            //Do your stuff here
            //textView.text = list.get(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //(holder as ViewHolder).bind(position)
        (holder as ViewHolder).textView.text = list[0]
        (holder as ViewHolder).tvPresentacionLista.setText(list[1])
        /*(holder as ViewHolder).tvDescripcionLista.setText(list[2])
        (holder as ViewHolder).tvCantidadLista.setText(list[3])
        (holder as ViewHolder).tvLoteLista.setText(list[4])
        (holder as ViewHolder).tvCaducidadLista.setText(list[5])
        (holder as ViewHolder).tvRemisionLista.setText(list[6])
        (holder as ViewHolder).tvProcedenciaLista.setText(list[7])*/
        //(holder as ViewHolder).tvPresentacionLista.setText(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}