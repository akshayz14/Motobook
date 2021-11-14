package com.apc.motobook.view.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.apc.motobook.DataBase.AppDataBase
import com.apc.motobook.R
import com.apc.motobook.databinding.FragmentAddVehiclesBinding
import com.apc.motobook.model.InsuranceDetails
import com.apc.motobook.repository.AddVehicleRepository
import com.apc.motobook.viewmodel.AddVehicleFragmentsViewModel
import com.apc.motobook.viewmodel.AddVehicleViewModelFactory
import kotlinx.android.synthetic.main.fragment_add_vehicles.*
import java.text.SimpleDateFormat
import java.util.*


class AddVehiclesFragment : Fragment(), View.OnClickListener {

    private lateinit var database: AppDataBase
    private lateinit var addVehiclesBinding: FragmentAddVehiclesBinding
    private val binding get() = addVehiclesBinding
    lateinit var addVehicleFragmentsViewModel: AddVehicleFragmentsViewModel
    lateinit var insuranceDetails: InsuranceDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addVehiclesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_vehicles, container, false
        )

        val dao = AppDataBase.getDataBase(requireActivity()).vehicleDetailsDao()
        val repository = AddVehicleRepository(dao)
        val factory = AddVehicleViewModelFactory(repository)

        addVehicleFragmentsViewModel =
            ViewModelProvider(this, factory).get(AddVehicleFragmentsViewModel::class.java)

        addVehiclesBinding.addvehicleFragmentViewModel = addVehicleFragmentsViewModel
        addVehiclesBinding.lifecycleOwner = this

        val view = binding.root
        addVehiclesBinding.etLastServiceDate.setOnClickListener(this)
        addVehiclesBinding.etNextServiceDate.setOnClickListener(this)
        addVehiclesBinding.btnAddInsurance.setOnClickListener(this)

        addVehiclesBinding.rbBike.setOnClickListener(this)
        addVehiclesBinding.rbCar.setOnClickListener(this)
        addVehiclesBinding.rbOther.setOnClickListener(this)
        addVehiclesBinding.btnAdd.setOnClickListener(this)

//        addVehicleFragmentsViewModel.isDateValidLiveData.observe( {
//            Log.d("TAG", "onCreateView: " + it)
//        })

//        addVehicleFragmentsViewModel.selectedRB.observe(viewLifecycleOwner, {
//            Log.d("selectedRB", "onCreateView: " + it)
//        })

        addVehicleFragmentsViewModel.addButtonClickedLiveData.observe(viewLifecycleOwner, {
            if (it == 1) {
                showToast()
                findNavController().navigate(R.id.action_addVehiclesFragment_to_homefragment)
            }
        })
        return view
    }

    private fun showToast() {
        Toast.makeText(context, "Data added to DB", Toast.LENGTH_SHORT).show()
    }

    private fun showLastServiceDatePickerDialog() {

        addVehiclesBinding.etLastServiceDate.setOnClickListener {

            val calendar: Calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(requireActivity(), { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                val format = "dd-MM-yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val date = sdf.format(calendar.time)
                binding.etLastServiceDate.setText(date)
                addVehicleFragmentsViewModel.setValueLastService(date)
            }, year, month, day)
            dpd.show()
        }

    }

    private fun showNextServiceDatePickerDialog() {

        addVehiclesBinding.etNextServiceDate.setOnClickListener {

            val calendar: Calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(requireActivity(), { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                val format = "dd-MM-yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val date = sdf.format(calendar.time)
                binding.etNextServiceDate.setText(date)
                addVehicleFragmentsViewModel.setValueNextService(date)
            }, year, month, day)
            dpd.show()
        }
    }


    override fun onClick(p0: View?) {

        when (p0) {
            binding.etLastServiceDate -> {
                showLastServiceDatePickerDialog();
            }

            binding.etNextServiceDate -> {
                showNextServiceDatePickerDialog();
            }

            binding.btnAddInsurance -> {
                showInsuranceDialog()
            }

            binding.rbBike -> {
                val selected = rgTypeOfVehicle.checkedRadioButtonId
                Log.d("TAG", "onClick: $selected ")
                addVehicleFragmentsViewModel.setVehicleType(2)
            }

            binding.rbCar -> {
                val selected = rgTypeOfVehicle.checkedRadioButtonId
                Log.d("TAG", "onClick: $selected ")
                addVehicleFragmentsViewModel.setVehicleType(1)
            }
            binding.rbOther -> {
                val selected = rgTypeOfVehicle.isSelected
                Log.d("TAG", "onClick: $selected ")
                addVehicleFragmentsViewModel.setVehicleType(3)
            }
        }
    }

    private fun showInsuranceDialog() {

        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.insurance_dialog_layout)
        val etInsuranceName = dialog.findViewById(R.id.etInsuranceName) as EditText
        val etInsuranceExpiryDate = dialog.findViewById(R.id.etInsuranceExpiryDate) as EditText
        val etInsuranceNumber = dialog.findViewById(R.id.etInsuranceNumber) as EditText

        etInsuranceExpiryDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(requireActivity(), { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                val format = "dd-MM-yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val date = sdf.format(calendar.time)
                etInsuranceExpiryDate.setText(date)
                addVehicleFragmentsViewModel.setValueInsuranceExpiry(date)
            }, year, month, day)
            dpd.show()
        }

        val btnAddInsuranceDetails = dialog.findViewById(R.id.btnAddInsuranceDetails) as Button
        btnAddInsuranceDetails.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}