package com.example.shoppinglist8.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist8.itemlist.ItemClickListener
import com.example.shoppinglist8.itemlist.ItemRepository
import com.example.shoppinglist8.utils.Item

class ItemViewModel(val repository: ItemRepository) : ViewModel(), ItemClickListener {
    private var _itemLiveData = MutableLiveData<List<Item>>()
        val itemLiveData: LiveData<List<Item>>
        get() {
            return _itemLiveData
        }

   // menjalankan suatu fungsi secara automatis saat memanggil kelas
    init {
        loadItemData()
    }

    fun loadItemData() {
        _itemLiveData.value = repository.list()
    }

    fun onAddItem(item: Item) {
        repository.add(item)
        loadItemData()
    }

    override fun onDelete(item: Item) {
        repository.delete(item)
        loadItemData()
    }

}