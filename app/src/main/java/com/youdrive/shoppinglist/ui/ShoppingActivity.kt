package com.youdrive.shoppinglist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.youdrive.shoppinglist.R
import com.youdrive.shoppinglist.data.db.ShoppingDatabase
import com.youdrive.shoppinglist.data.db.entities.ShoppingItem
import com.youdrive.shoppinglist.data.repository.ShoppingRepository
import com.youdrive.shoppinglist.other.ShoppingItemAdapter
import com.youdrive.shoppinglist.ui.shoppinglist.AddDialogListener
import com.youdrive.shoppinglist.ui.shoppinglist.AddShoppingItemDialog
import com.youdrive.shoppinglist.ui.shoppinglist.ShoppingViewModel
import com.youdrive.shoppinglist.ui.shoppinglist.ShoppingViewModelFactory

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val rv = findViewById<RecyclerView>(R.id.rvShoppingItems)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}