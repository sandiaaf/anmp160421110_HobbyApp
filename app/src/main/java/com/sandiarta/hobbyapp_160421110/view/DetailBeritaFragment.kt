package com.sandiarta.hobbyapp_160421110.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sandiarta.hobbyapp_160421110.R
import com.sandiarta.hobbyapp_160421110.databinding.FragmentDetailBeritaBinding
import com.sandiarta.hobbyapp_160421110.viewmodel.DetailBeritaViewModel
import com.sandiarta.hobbyapp_160421110.viewmodel.ListBeritaViewModel
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit


class DetailBeritaFragment : Fragment() {
    private lateinit var binding: FragmentDetailBeritaBinding
    private lateinit var viewModel:DetailBeritaViewModel

    var descriptionsList: List<String> = listOf()
    var currentIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBeritaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailBeritaViewModel::class.java)
        var id = DetailBeritaFragmentArgs.fromBundle(requireArguments()).idBerita;
        viewModel.fetch(id)
        observeViewModel()

        binding.buttonNextPage.setOnClickListener {
            if (currentIndex < descriptionsList.size - 1) {
                currentIndex++
                updateDescription()
            }
        }
        binding.buttonPrevPage.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateDescription()
            }
        }
    }
    fun observeViewModel() {
        viewModel.beritaLD.observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it.image).into(binding.imageViewBerita)
            binding.textViewTitle.setText(it.title)
            binding.textViewUser.setText(it.name)
            binding.textViewSubtitle.setText(it.subtitle)

            descriptionsList = it.descriptions

            binding.textViewDesc.setText(it.descriptions[0])
        })

    }
    fun updateDescription() {
        val currentDescription = descriptionsList[currentIndex]
        binding.textViewDesc.setText(currentDescription)

        binding.buttonNextPage.isEnabled = currentIndex < descriptionsList.size - 1
        binding.buttonPrevPage.isEnabled = currentIndex > 0
    }

}