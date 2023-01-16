package com.kkk.justodo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.kkk.justodo.R
import com.kkk.justodo.ToDoApplication
import com.kkk.justodo.databinding.ActivityMainBinding
import com.kkk.justodo.model.Item
import com.kkk.justodo.util.AdapterItemClickListener
import com.kkk.justodo.util.MainAdapter
import com.kkk.justodo.viewmodel.MainViewModel
import com.kkk.justodo.viewmodel.MainViewModelFactory


class MainActivity : AppCompatActivity(), AdapterItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: MainAdapter

    private val mainViewModel by viewModels<MainViewModel> {
        MainViewModelFactory((application as ToDoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        binding.viewModel = mainViewModel

        listAdapter = MainAdapter(this)
        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.adapter = listAdapter

        mainViewModel.allItemsByCompletion.observe(this){
            Log.i("kkkCat", it.toString())
            listAdapter.submitList(it)
        }

        binding.btnSubmit.setOnClickListener{
            mainViewModel.insertOrUpdate()
            binding.etTxt.setText("")
        }
    }

    override fun onItemClick(item: Item) {
        binding.etTxt.setText(item.body)
        mainViewModel.apply{
            selectedItemId = item.id
            isUpdateEnabled = true
        }
    }

    override fun onClickDelete(item: Item) {
        mainViewModel.delete(item)
    }

    override fun onClickDone(item: Item) {
        item.isDone = !item.isDone
        mainViewModel.update(item)
    }

    override fun onClickImportant(item: Item) {
        item.isImportant = !item.isImportant
        mainViewModel.update(item)
    }

}