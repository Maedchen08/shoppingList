package com.example.shoppinglist8.itemlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist8.R
import com.example.shoppinglist8.databinding.FragmentAddItemBinding
import com.example.shoppinglist8.databinding.FragmentListItemBinding
import com.example.shoppinglist8.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_list_item.*

class ListItemFragment : Fragment() {
    lateinit var viewModel: ItemViewModel
    lateinit var binding: FragmentListItemBinding
    lateinit var rvAdapter: RecylerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentListItemBinding.inflate(layoutInflater)
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvAdapter = RecylerAdapter(viewModel)
            recycler_view.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = rvAdapter
            }
        }

    }

    private fun subscribe() {
        viewModel.itemLiveData.observe(this) {
            rvAdapter.setData(it)
        }

    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ItemRepositoryImplement()
                return ItemViewModel(repo) as T
            }
        }

        ).get(ItemViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ListItemFragment()
    }


}