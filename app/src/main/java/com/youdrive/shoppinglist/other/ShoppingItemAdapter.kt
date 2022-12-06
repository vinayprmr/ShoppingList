package com.youdrive.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.youdrive.shoppinglist.R
import com.youdrive.shoppinglist.data.db.entities.ShoppingItem
import com.youdrive.shoppinglist.ui.shoppinglist.ShoppingViewModel


class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private var viewModel: ShoppingViewModel,
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]
        val v: View = holder.itemView

        val tvName = v.findViewById<TextView>(R.id.tvName)
        val tvAmount = v.findViewById<TextView>(R.id.tvAmount)
        val ivDelete = v.findViewById<ImageView>(R.id.ivDelete)
        val ivPlus = v.findViewById<ImageView>(R.id.ivPlus)
        val ivMinus = v.findViewById<ImageView>(R.id.ivMinus)

        tvName.text = curShoppingItem.name
        tvAmount.text = curShoppingItem.amount.toString()

        ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }
        ivPlus.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }
        ivMinus.setOnClickListener {
            if (curShoppingItem.amount>0){
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}