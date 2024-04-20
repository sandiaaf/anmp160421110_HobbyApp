package com.sandiarta.hobbyapp_160421110.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sandiarta.hobbyapp_160421110.databinding.FragmentRegisterBinding
import com.sandiarta.hobbyapp_160421110.viewmodel.AccountViewModel


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).OffMenu()

        binding.buttonRegister.setOnClickListener {
            val username = binding.editTextUsername1.text.toString()
            val password = binding.editTextPassword.text.toString()
            val repass = binding.editTextRepassword.text.toString()
            val nama_depan = binding.editTextNamaDepan.text.toString()
            val nama_belakang = binding.editTextNamaBelakang.text.toString()
            val email = binding.editTextEmail.text.toString()

            if (username.isEmpty() || password.isEmpty() || repass.isEmpty() || nama_depan.isEmpty() || nama_belakang.isEmpty() || email.isEmpty()) {
                Toast.makeText(context, "Mohon isi semua kolomnya", Toast.LENGTH_SHORT).show()
            }else{
                if (password == repass){
                    viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
                    viewModel.register(username,nama_depan,nama_belakang,email,password)

                    viewModel.navigatePage.observe(viewLifecycleOwner, Observer {
                        if(it == true) {
                            val action = RegisterFragmentDirections.actionLoginFragment()
                            Navigation.findNavController(requireView()).navigate(action)

                            viewModel.navigatePage.value = false
                        } else {
                        }
                    })
                }
                else{
                    Toast.makeText(context, "Password dan Re-Password tidak sama", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.buttonLoginPage.setOnClickListener {
            val action = RegisterFragmentDirections.actionLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}