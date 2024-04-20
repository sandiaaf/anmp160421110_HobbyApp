package com.sandiarta.hobbyapp_160421110.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandiarta.hobbyapp_160421110.R
import com.sandiarta.hobbyapp_160421110.databinding.FragmentHomeBinding
import com.sandiarta.hobbyapp_160421110.viewmodel.ListBeritaViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel:ListBeritaViewModel
    private val beritaListAdapter  = BeritaListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).OnMenu()

        viewModel = ViewModelProvider(this).get(ListBeritaViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = beritaListAdapter

//        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
//        binding.refreshLayout.setOnRefreshListener {
//            viewModel.refresh()
//            binding.recView.visibility =  View.GONE
//            binding.textViewError.visibility = View.GONE
//            binding.progressBarLoad.visibility = View.VISIBLE
//            binding.refreshLayout.isRefreshing = false
//        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.beritasLD.observe(viewLifecycleOwner, Observer {
            beritaListAdapter.updateBeritaList(it)
        })
//        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
//            if(it == true) {
//                binding.recView.visibility = View.GONE
//                binding.progressBarLoad.visibility = View.VISIBLE
//            } else {
//                binding.recView.visibility = View.VISIBLE
//                binding.progressBarLoad.visibility = View.GONE
//            }
//        })
//        viewModel.catLoadErrorLD.observe(viewLifecycleOwner, Observer {
//            if (it == true) {
//                binding.textViewError?.visibility = View.VISIBLE
//            }
//            else {
//                binding.textViewError?.visibility = View.GONE
//            }
//        })

    }

}