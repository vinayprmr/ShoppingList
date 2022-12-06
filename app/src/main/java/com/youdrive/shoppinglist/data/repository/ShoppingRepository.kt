package com.youdrive.shoppinglist.data.repository

import com.youdrive.shoppinglist.data.db.ShoppingDatabase
import com.youdrive.shoppinglist.data.db.entities.ShoppingItem

class ShoppingRepository(
    private var db: ShoppingDatabase,
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItem() = db.getShoppingDao().getAllShoppingItems()

}