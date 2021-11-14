package com.apc.motobook.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.apc.motobook.DataBase.AppDataBase
import com.apc.motobook.R
import com.apc.motobook.databinding.FragmentHomeBinding
import com.apc.motobook.viewmodel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: FragmentHomeBinding
    private lateinit var database : AppDataBase

    private val binding get() = homeFragmentBinding
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        database = AppDataBase.getDataBase(requireContext().applicationContext)

        val view = binding.root
        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        homeFragmentBinding.homeViewModel = homeFragmentViewModel
//        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController

        homeFragmentBinding.lifecycleOwner = this
        homeFragmentBinding.btnAddVehicle.setOnClickListener {
            homeFragmentBinding.btnAddVehicle.findNavController().navigate(R.id.action_homeFragment_to_addVehiclesFragment)
        }

        homeFragmentBinding.btnShowMyVehicles.setOnClickListener {
            homeFragmentBinding.btnShowMyVehicles.findNavController().navigate(R.id.action_homeFragment_to_showVehiclesFragment)
        }

        return view
    }

//    private fun openShowVehicleFragment() {
//        val transaction = requireActivity().supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container_view, ShowVehiclesFragment())
//        transaction.addToBackStack("ShowVehiclesFragment")
//        transaction.commit()
//
//    }
//
//    fun openAddVehicleFragment() {
//        val transaction = requireActivity().supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container_view, AddVehiclesFragment())
//        transaction.addToBackStack("AddVehiclesFragment")
//        transaction.commit()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
//        fragBinding = null
    }




}