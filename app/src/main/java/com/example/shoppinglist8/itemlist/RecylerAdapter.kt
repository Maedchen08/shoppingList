package com.example.shoppinglist8.itemlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist8.R
import com.example.shoppinglist8.databinding.ActivityMainBinding.inflate
import com.example.shoppinglist8.utils.Item
import kotlinx.android.synthetic.main.card_view.view.*

class RecylerAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<ItemViewHolder>() {

//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var itemName: TextView = itemView.findViewById(R.id.tvItemName)
//        var itemQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
//        var itemNote: TextView = itemView.findViewById(R.id.tvNote)
//        var itemDate: TextView = itemView.findViewById(R.id.tvDate)
//    }

    var items = ArrayList<Item>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val itemView =  inflater.inflate(R.layout.card_view, parent, false)
        return ItemViewHolder(itemView,itemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = items[position]
        holder.bind(item)
    }

    fun setData(newItemList:List<Item>) {
        items.clear()
        items.addAll(newItemList)
        notifyDataSetChanged()
    }


}