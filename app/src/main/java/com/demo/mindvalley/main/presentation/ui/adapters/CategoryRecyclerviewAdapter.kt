package com.demo.mindvalley.main.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.mindvalley.R
import com.demo.mindvalley.main.data.models.categoriesmodel.Category


class CategoryRecyclerviewAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        return holder.bind(categories[position])
    }
}

class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.tvCategoryTitle)

    fun bind(category: Category) {
        if (category.name.isNotEmpty())
            title.text = category.name
        else
            title.text = title.context.getString(R.string.no_category_available)
    }

}

