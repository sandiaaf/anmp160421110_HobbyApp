package com.sandiarta.hobbyapp_160421110.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sandiarta.hobbyapp_160421110.databinding.FragmentLoginBinding
import com.sandiarta.hobbyapp_160421110.viewmodel.AccountViewModel
import com.squareup.picasso.Picasso


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).OffMenu()

        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Mohon isi semua kolomnya", Toast.LENGTH_SHORT).show()
            }else{
                viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
                viewModel.login(username,password)

                viewModel.accountLD.observe(viewLifecycleOwner, Observer {
                    var sharedFile = "com.sandiarta.hobbyapp_160421110"
                    var shared: SharedPreferences =requireContext().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

                    var editor: SharedPreferences.Editor = shared.edit()
                    editor.putInt("ID_ACCOUNT",it.id)
                    editor.putString("USERNAME",it.username)
                    editor.putString("NAMA_DEPAN",it.nama_depan)
                    editor.putString("NAMA_BELAKANG",it.nama_belakang)
                    editor.putString("EMAIL",it.email)
                    editor.apply()

                    Log.d("test",it.toString())

                })
                viewModel.navigatePage.observe(viewLifecycleOwner, Observer {
                    if(it == true) {
                        val action = LoginFragmentDirections.actionItemHome()
                        Navigation.findNavController(requireView()).navigate(action)

                        viewModel.navigatePage.value = false
//                        Log.d("test",it.toString())
//                        Navigation.findNavController(requireView()).popBackStack()
//                        Log.d("test",it.toString())
                    } else {
                    }
                })

            }
        }
        binding.buttonCreateAccount.setOnClickListener {
            val action = LoginFragmentDirections.actionRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}