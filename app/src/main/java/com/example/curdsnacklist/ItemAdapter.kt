package com.example.curdsnacklist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.curdsnacklist.databinding.CurdSnackImageBinding

class ItemAdapter(val curdSnackList: ArrayList<CurdSnack>, val launchFragment: (curdSnack: CurdSnack) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemHolder>() {

    private var selected_item_position = -1

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CurdSnackImageBinding.bind(view)

        fun bind(curdSnack: CurdSnack, position: Int) {
            Glide
                    .with(binding.root)
                    .load(curdSnack.image)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.rvImageCurdSnack)

            // change item sate
            if (selected_item_position == position) {
                binding.itemSelected.isSelected = true
            }
            else {
                binding.itemSelected.isSelected = false
            }

            binding.rvItem.setOnClickListener {
                if(selected_item_position != adapterPosition) {
                    Log.d("ItemAdapter", "Before Context: $this")

                    // previous item state: not selected
                    notifyItemChanged(selected_item_position)

                    Log.d("ItemAdapter", "After first notify Context: $this")

                    selected_item_position = adapterPosition

                    // current item state: selected
                    notifyItemChanged(selected_item_position)

                    Log.d("ItemAdapter", "After second notify Context: $this")
                    // notify MainActivity that item position has been selected
                    MainActivity.newItemSelected(selected_item_position)

                    // launch info fragment
                    launchFragment(curdSnackList[selected_item_position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.curd_snack_image, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return curdSnackList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(curdSnackList[position], position)
    }

    //change item sate in recycler view when item was selected without using recycler view elements
    fun selectedCurdSnack(position: Int) {
        notifyItemChanged(selected_item_position)

        selected_item_position = position

        notifyItemChanged(selected_item_position)
    }
}