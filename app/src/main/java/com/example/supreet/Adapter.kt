package com.example.supreet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(var recyclerArray: ArrayList<phone> =ArrayList()):
    RecyclerView.Adapter<Adapter.AdapterHolder>() {
    class AdapterHolder(view: View): RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var phonenumber: TextView = view.findViewById(R.id.phonenumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.phonecall,parent,false)
        return AdapterHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.name.setText("${recyclerArray.get(position).name}")
        holder.phonenumber.setText("${recyclerArray.get(position).phonenumber}")
    }

    override fun getItemCount(): Int {
        return recyclerArray.size
    }
}