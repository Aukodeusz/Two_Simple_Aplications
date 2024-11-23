package com.example.movietraker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val genreTextView: TextView = view.findViewById(R.id.genreTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val ratingTextView: TextView = view.findViewById(R.id.ratingTextView)
        val watchedCheckBox: CheckBox = view.findViewById(R.id.watchedCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.titleTextView.text = item.title
        holder.genreTextView.text = item.genre
        holder.descriptionTextView.text = item.description
        holder.ratingTextView.text = item.rating.toString()
        holder.watchedCheckBox.isChecked = item.isWatched

        holder.itemView.setOnClickListener {
            showItemDetails(item, it.context)
        }

        holder.watchedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            item.isWatched = isChecked
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = itemList.size

    private fun showItemDetails(item: Item, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(item.title)
        builder.setMessage("Gatunek: ${item.genre}\nOpis: ${item.description}\nOcena: ${item.rating}")
        builder.setPositiveButton("OK", null)
        builder.show()
    }
}
