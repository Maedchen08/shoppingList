package com.example.shoppinglist8.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shoppinglist8.utils.Item

class ItemViewModel : ViewModel() {
    private  var ListItem = mutableListOf<Item>()

    fun addItem(item:Item) {
        ListItem.add(item)
    }

    fun getItem() : MutableList<Item> {
        return ListItem
    }
}