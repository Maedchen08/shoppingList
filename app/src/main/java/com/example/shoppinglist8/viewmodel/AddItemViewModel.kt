package com.example.shoppinglist8.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist8.utils.Item
import com.example.shoppinglist8.utils.ResourceState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddItemViewModel : ViewModel() {
    private var _isValid = MutableLiveData<ResourceState>()
    val isValid : LiveData<ResourceState>
    get() {
        return _isValid
    }

    fun validationItem(item:Item) {
        GlobalScope.launch {
            _isValid.postValue(ResourceState.loading())
            delay(3000)
            if (item.date.isNullOrBlank()) {
                _isValid.postValue(ResourceState.fail("Date can not empty"))

            } else if (item.itemName.isNullOrBlank()) {
                _isValid.postValue(ResourceState.fail("Item name can empty"))
            } else if (item.note.isNullOrBlank()) {
                _isValid.postValue(ResourceState.fail("Note can not empty"))
            } else if (item.quantity.toString().isNullOrBlank()&& item.quantity <= 0) {
                _isValid.postValue(ResourceState.fail("note can not empty"))
            } else {
                _isValid.postValue(ResourceState.success(true))
            }

        }
    }

}