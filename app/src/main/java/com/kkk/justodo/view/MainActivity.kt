package com.kkk.justodo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kkk.justodo.R
import com.kkk.justodo.databinding.ActivityMainBinding
import com.kkk.justodo.util.IClickListener
import com.kkk.justodo.util.MainAdapter
import com.kkk.justodo.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(), IClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var listAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this@MainActivity, ViewModelProvider.AndroidViewModelFactory(application))[MainViewModel::class.java]
        Log.i("kkkCat", mainViewModel.toString())
        binding.viewModel = mainViewModel

        listAdapter = MainAdapter(this)
        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.adapter = listAdapter

        mainViewModel.getAll().observe(this){
            listAdapter.submitList(it)
        }
    }

    override fun onClickRoot(i: String) {
        Toast.makeText(this, i, Toast.LENGTH_SHORT).show()
    }

    //TODO: Configure Input
    //TODO: @XML databind Expression
}