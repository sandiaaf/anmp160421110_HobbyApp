package com.sandiarta.hobbyapp_160421110.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandiarta.hobbyapp_160421110.R
import com.sandiarta.hobbyapp_160421110.databinding.FragmentProfileBinding
import com.sandiarta.hobbyapp_160421110.databinding.FragmentReadingHistoryBinding


class ReadingHistoryFragment : Fragment() {
    private lateinit var binding: FragmentReadingHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReadingHistoryBinding.inflate(inflater,container,false)
        return binding.root
    }

}