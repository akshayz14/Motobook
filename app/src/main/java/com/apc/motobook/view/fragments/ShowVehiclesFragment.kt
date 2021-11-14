package com.apc.motobook.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.apc.motobook.DataBase.AppDataBase
import com.apc.motobook.R
import com.apc.motobook.adapter.VehiclesRecyclerViewAdapter
import com.apc.motobook.databinding.FragmentShowVehiclesBinding
import com.apc.motobook.model.VehicleDetails
import com.apc.motobook.repository.AddVehicleRepository
import com.apc.motobook.viewmodel.ShowVehicleViewModelFactory
import com.apc.motobook.viewmodel.ShowVehiclesViewModel


class ShowVehiclesFragment : Fragment() {

    private lateinit var showVehiclesBinding: FragmentShowVehiclesBinding
    private lateinit var showVehicleViewModel: ShowVehiclesViewModel
    private lateinit var vehicleAdapter: VehiclesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        showVehiclesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_show_vehicles, container, false
        )

        val dao = AppDataBase.getDataBase(requireActivity()).vehicleDetailsDao()
        val repository = AddVehicleRepository(dao)
        val factory = ShowVehicleViewModelFactory(repository)

        showVehicleViewModel =
            ViewModelProvider(this, factory).get(ShowVehiclesViewModel::class.java)

        showVehiclesBinding.showVehicleFragmentViewModel = showVehicleViewModel
        showVehiclesBinding.lifecycleOwner = this
        val view = showVehiclesBinding.root

        initRecyclerView()
        return view
    }

    private fun listItemClicked(vehicleDetails: VehicleDetails) {
        Toast.makeText(
            context,
            "selected vehicle model is ${vehicleDetails.model}",
            Toast.LENGTH_LONG
        )
            .show()
    }

    private fun initRecyclerView() {

        showVehiclesBinding.subscriberRecyclerView.layoutManager = LinearLayoutManager(context)

        vehicleAdapter = VehiclesRecyclerViewAdapter({ selectedItem: VehicleDetails ->
            listItemClicked(selectedItem)
        })
        showVehiclesBinding.subscriberRecyclerView.adapter = vehicleAdapter
        displayVehicleList()
    }

    private fun displayVehicleList() {
        showVehicleViewModel.getAllSavedVehicles().observe(viewLifecycleOwner, {
            vehicleAdapter.setList(it)
            vehicleAdapter.notifyDataSetChanged()
        })
    }

}