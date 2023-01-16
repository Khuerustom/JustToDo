package com.kkk.justodo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kkk.justodo.R
import com.kkk.justodo.ToDoApplication
import com.kkk.justodo.databinding.ActivityMainBinding
import com.kkk.justodo.model.Item
import com.kkk.justodo.util.IClickListener
import com.kkk.justodo.util.MainAdapter
import com.kkk.justodo.viewmodel.MainViewModel
import com.kkk.justodo.viewmodel.MainViewModelFactory


class MainActivity : AppCompatActivity(), IClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var listAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        //TODO: Change instantiation
        mainViewModel = ViewModelProvider(
            this@MainActivity,
            MainViewModelFactory(
                (application as ToDoApplication)
                .repository)
        )[MainViewModel::class.java]

        //Log.i("kkkCat", mainViewModel.toString())

        binding.viewModel = mainViewModel

        listAdapter = MainAdapter(this)
        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.adapter = listAdapter

        mainViewModel._allItems.observe(this){
            Log.i("kkkCat", it.toString())
            listAdapter.submitList(it)
        }
    }

    override fun onClickRoot(i: Item) {
        binding.etTxt.setText(i.body)
        mainViewModel.apply{
            selectedItemId = i.id
            isUpdateEnabled = true
        }
        Toast.makeText(this, i.body, Toast.LENGTH_SHORT).show()
    }

    //TODO: Configure Input
    //TODO: @XML databind Expression
}