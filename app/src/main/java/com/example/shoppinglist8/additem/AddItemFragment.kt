package com.example.shoppinglist8.additem

import androidx.appcompat.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist8.R
import com.example.shoppinglist8.components.LoadingDialog
import com.example.shoppinglist8.databinding.FragmentAddItemBinding
import com.example.shoppinglist8.itemlist.ItemRepositoryImplement
import com.example.shoppinglist8.utils.Item
import com.example.shoppinglist8.utils.ResourceStatus
import com.example.shoppinglist8.viewmodel.AddItemViewModel
import com.example.shoppinglist8.viewmodel.ItemViewModel
import java.util.*

class AddItemFragment : Fragment() {
    lateinit var viewModel: AddItemViewModel
    lateinit var sharedViewModel: ItemViewModel
    lateinit var binding: FragmentAddItemBinding
    lateinit var loadingDialog: AlertDialog
    var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadingDialog = LoadingDialog.build(requireContext())
        binding = FragmentAddItemBinding.inflate(layoutInflater)
        binding.apply {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            etShopDate.setInputType(InputType.TYPE_NULL)
            etShopDate.setOnClickListener(View.OnClickListener {
                val datePickerDialog = activity?.let { it1 ->
                    DatePickerDialog(
                        it1, DatePickerDialog.OnDateSetListener
                        { view, year, monthOfYear, dayOfMonth ->
                            etShopDate.setText(
                                "$year/$monthOfYear/$dayOfMonth",
                                TextView.BufferType.EDITABLE
                            );
                        }, year, month, day
                    )
                }
                datePickerDialog?.show()
            })

            buttonAdd.setOnClickListener {
                var intQty = 0
                if (!etQuantity.text.toString().isNullOrEmpty()) {
                    intQty = etQuantity.text.toString().toInt()
                }
                item = Item(
                    date = etShopDate.text.toString(),
                    quantity = intQty,
                    note = etNote.text.toString(),
                    itemName = etItem.text.toString()
                )
                viewModel.validationItem(item!!)

            }
        }
        return binding.root
    }

    private fun initViewModel() {
        sharedViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ItemRepositoryImplement()
           return ItemViewModel(repo) as T
            }
        }).get(ItemViewModel::class.java)
            viewModel = ViewModelProvider(this).get(AddItemViewModel::class.java)
    }

    private fun subscribe() = viewModel.isValid.observe(this) {
        when (it.status) {
            ResourceStatus.LOADING -> {
                loadingDialog.show()
            }
            ResourceStatus.SUCCESS -> {
                sharedViewModel.onAddItem(item!!)
                clearInput()
                loadingDialog.hide()
                Toast.makeText(
                    requireContext(),
                    "Data has been saved!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            ResourceStatus.FAIL -> {
                loadingDialog.hide()
                Toast.makeText(
                    requireContext(),
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun clearInput() {
        binding.etShopDate.setText("")
        binding.etQuantity.setText("")
        binding.etItem.setText("")
        binding.etNote.setText("")
    }


    companion object {
        fun newInstance() =
            AddItemFragment()
    }
}