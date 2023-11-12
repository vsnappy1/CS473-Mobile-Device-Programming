package com.kumar.mdp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kumar.mdp.data.Product
import com.kumar.mdp.databinding.ItemElectronicsBinding

class ElectronicItemAdapter(
    private val products: List<Product>,
    val onItemClick: (Product) -> Unit,
    val onAddItemClick: (Product) -> Unit
) :
    RecyclerView.Adapter<ElectronicItemAdapter.ElectronicItemViewHolder>() {

    inner class ElectronicItemViewHolder(val binding: ItemElectronicsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                imageViewProduct.setImageResource(product.image)
                imageViewLogo.setImageResource(product.logo)
                textViewProductName.text = product.name
                textViewProductDescription.text = product.description
                textViewProductCost.text = "${product.price}"
                buttonAdd.setOnClickListener {
                    onAddItemClick(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectronicItemViewHolder {
        val binding =
            ItemElectronicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElectronicItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ElectronicItemViewHolder, position: Int) {
        holder.bind(products[position])
        onItemClick(products[position])
    }
}