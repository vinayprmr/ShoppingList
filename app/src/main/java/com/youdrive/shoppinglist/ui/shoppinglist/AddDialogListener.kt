package com.youdrive.shoppinglist.ui.shoppinglist

import com.youdrive.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item:ShoppingItem)
}