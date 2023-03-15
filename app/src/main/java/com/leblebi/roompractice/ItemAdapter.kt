package com.leblebi.roompractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(val onClickListener:(User)->Unit):RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {


    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rec_adapter,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.agetext).text = currentItem.age.toString()
        holder.itemView.findViewById<TextView>(R.id.nametext).text = currentItem.name

        holder.itemView.setOnClickListener{
            onClickListener(currentItem)

        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(users:List<User>){
        this.userList = users
    }


}