package com.android.training.valesalmacenj16.classes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.training.valesalmacenj16.R

class ValeMedicamentosAdapter (private val context : Context, private val list : ArrayList<ValeMedicamentosModel>) : RecyclerView.Adapter<ValeMedicamentosAdapter.ViewHolder>() {
    inner class ViewHolder internal constructor(itemView : View) : RecyclerView.ViewHolder(itemView){

        var txtClaveListaModel : TextView
        var txtPresentacionListaModel : TextView
        var txtDescripcionListaModel : TextView
        var txtCantidadListaModel : TextView
        var txtLoteListaModel : TextView
        var txtFechaCaducidadModel : TextView
        var txtRemisionListaModel : TextView
        var txtProcedenciaListaModel : TextView

        init {
            txtClaveListaModel = itemView.findViewById(R.id.textViewClaveLista) as TextView
            txtPresentacionListaModel = itemView.findViewById(R.id.textViewPresentacionLista) as TextView
            txtDescripcionListaModel = itemView.findViewById(R.id.textViewDescripcionLista) as TextView
            txtCantidadListaModel = itemView.findViewById(R.id.textViewCantidadLista) as TextView
            txtLoteListaModel = itemView.findViewById(R.id.textViewLoteLista) as TextView
            txtFechaCaducidadModel = itemView.findViewById(R.id.textViewCaducidadLista) as TextView
            txtRemisionListaModel=itemView.findViewById(R.id.textViewRemisionLista) as TextView
            txtProcedenciaListaModel=itemView.findViewById(R.id.textViewProcLista) as TextView
        }

        internal fun bind(position : Int) {
            txtClaveListaModel.setText(list[position].getClaveLista())
            txtPresentacionListaModel.setText(list[position].getPresentacionLista())
            txtDescripcionListaModel.setText(list[position].getDescripcionLista())
            txtCantidadListaModel.setText(list[position].getCantidadLista())
            txtLoteListaModel.setText(list[position].getLoteLista())
            txtFechaCaducidadModel.setText(list[position].getFechaCad())
            txtRemisionListaModel.setText(list[position].getRemision())
            txtProcedenciaListaModel.setText(list[position].getProcedencia())
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelo : ValeMedicamentosModel = list.get(position)
        holder.txtClaveListaModel.setText(modelo.getClaveLista())
        holder.txtPresentacionListaModel.setText(modelo.getPresentacionLista())
        holder.txtDescripcionListaModel.setText(modelo.getDescripcionLista())
        holder.txtCantidadListaModel.setText(modelo.getCantidadLista())
        holder.txtLoteListaModel.setText(modelo.getLoteLista())
        holder.txtFechaCaducidadModel.setText(modelo.getFechaCad())
        holder.txtRemisionListaModel.setText(modelo.getRemision())
        holder.txtProcedenciaListaModel.setText(modelo.getProcedencia())
        //holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
