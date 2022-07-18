package com.platformscience.challenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.platformscience.challenge.data.model.Shipment
import com.platformscience.challenge.databinding.ContentShipmentBinding

class ShipmentListAdapter() : RecyclerView.Adapter<ShipmentViewHolder>() {

    var shipments = mutableListOf<Shipment>()

    fun setShipmentList(shipments: List<Shipment>) {
        this.shipments = shipments.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ContentShipmentBinding.inflate(inflater, parent, false)
        return ShipmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShipmentViewHolder, position: Int) {
        val shipment = shipments[position]

        holder.binding.driver.text = shipment.driver
        holder.binding.address.text = shipment.address

        holder.itemView.setOnClickListener {
            holder.binding.address.visibility =
                if (holder.binding.address.visibility == View.VISIBLE)
                    View.GONE else View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return shipments.size
    }
}

class ShipmentViewHolder(
    val binding: ContentShipmentBinding
) : RecyclerView.ViewHolder(binding.root)

