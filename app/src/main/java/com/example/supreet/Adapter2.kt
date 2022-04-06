package com.example.supreet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter2 (var recyclerArray: ArrayList<query> =ArrayList()
): RecyclerView.Adapter<Adapter2.AdapterHolder>() {
    class AdapterHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvname: TextView = view.findViewById(R.id.tvname)
        var tvclass:TextView = view.findViewById(R.id.tvclass)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter2.AdapterHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.activity_query,parent,false)
        return AdapterHolder(itemView)

    }

    override fun onBindViewHolder(holder: Adapter2.AdapterHolder, position: Int) {

//        holder.tvdate.setText("${recyclerArray.get(position).date}")


    }

    override fun getItemCount(): Int {
        return recyclerArray.size
    }


}

