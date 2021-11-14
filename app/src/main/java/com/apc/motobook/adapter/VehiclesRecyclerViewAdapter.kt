package com.apc.motobook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.apc.motobook.R
import com.apc.motobook.databinding.VehicleListItemBinding
import com.apc.motobook.model.VehicleDetails

/**
 * Created by Akshay on 06/09/21
 */
class VehiclesRecyclerViewAdapter(private val clickListener: (VehicleDetails) -> Unit) : RecyclerView.Adapter<VehiclesViewHolder>() {

    private val vehicleList = ArrayList<VehicleDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<VehicleListItemBinding>(layoutInflator,
                R.layout.vehicle_list_item, parent, false)
        return VehiclesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
        holder.bind(vehicleList[position], clickListener)
    }

    override fun getItemCount(): Int {

        return vehicleList.size
    }

    fun setList(vehicles: List<VehicleDetails>) {
        vehicleList.clear()
        vehicleList.addAll(vehicles)
    }
}

class VehiclesViewHolder(val binding: VehicleListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vehicleDetails: VehicleDetails, clickListener: (VehicleDetails) -> Unit) {
        binding.tvVehicleType.text = vehicleDetails.vehicleType
        binding.tvLastServiceDate.text = vehicleDetails.lastServiceDate.toString()
        binding.tvNextServiceDate.text = vehicleDetails.nextServiceDate.toString()
        binding.tvInsuranceExpiryDate.text = vehicleDetails.insuranceDetails.expiryDate.toString()
        binding.tvVehicleRegistration.text = vehicleDetails.registrationNumber
        binding.tvServiceInterval.text = vehicleDetails.serviceInterval.toString()
        binding.tvModel.text = vehicleDetails.model

        binding.listItemLayout.setOnClickListener {
            clickListener(vehicleDetails)
        }
    }
}