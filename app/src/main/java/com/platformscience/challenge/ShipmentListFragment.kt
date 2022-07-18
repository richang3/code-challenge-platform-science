package com.platformscience.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.platformscience.challenge.data.ShipmentApi
import com.platformscience.challenge.data.ShipmentsRepository
import com.platformscience.challenge.data.ShipmentsViewModelFactory
import com.platformscience.challenge.databinding.FragmentShipmentListBinding

class ShipmentListFragment : Fragment() {
    private var viewManager = LinearLayoutManager(context)
    private lateinit var apiService: ShipmentApi
    private var _binding: FragmentShipmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ShipmentListAdapter
    private lateinit var viewModel: ShipmentListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.fragment_drivers)
        _binding = FragmentShipmentListBinding.inflate(inflater, container, false)

        apiService = ShipmentApi(requireContext())
        viewModel = ViewModelProvider(
            this, ShipmentsViewModelFactory(
                ShipmentsRepository(apiService)
            )
        ).get(ShipmentListViewModel::class.java)

        adapter = ShipmentListAdapter()
        binding.driverList.adapter = adapter
        binding.driverList.layoutManager = viewManager

        val dividerItemDecoration = DividerItemDecoration(
            binding.driverList.context, viewManager.orientation
        )
        binding.driverList.addItemDecoration(dividerItemDecoration)

        viewModel.shipments.observe(viewLifecycleOwner) {
            adapter.setShipmentList(it)
        }

        viewModel.getShipments()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}