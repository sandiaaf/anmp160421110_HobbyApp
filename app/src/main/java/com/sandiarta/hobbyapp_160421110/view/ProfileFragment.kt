package com.sandiarta.hobbyapp_160421110.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sandiarta.hobbyapp_160421110.databinding.FragmentProfileBinding
import com.sandiarta.hobbyapp_160421110.viewmodel.AccountViewModel


class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel: AccountViewModel

    var id_account = 0
    var username:String? = ""
    var nama_depan:String? = ""
    var nama_belakang:String? = ""
    var email:String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sharedFile = "com.sandiarta.hobbyapp_160421110"
        var shared: SharedPreferences = requireContext().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        this.id_account = shared.getInt("ID_ACCOUNT",0)
        this.username= shared.getString("USERNAME","")
        this.nama_depan = shared.getString("NAMA_DEPAN","")
        this.nama_belakang = shared.getString("NAMA_BELAKANG","")
        this.email = shared.getString("EMAIL","")

        binding.editTextUsername1.setText(this.username)
        binding.editTextNamaDepan.setText(this.nama_depan)
        binding.editTextNamaBelakang.setText(this.nama_belakang)
        binding.editTextEmail.setText(this.email)

        binding.buttonChange.setOnClickListener {
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
                    viewModel.updateAccount(id_account,username,nama_depan,nama_belakang,email,password)
                }
                else{
                    Toast.makeText(context, "Password dan Re-Password tidak sama", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.buttonLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = shared.edit()
            editor.remove("ID_ACCOUNT")
            editor.remove("USERNAME")
            editor.remove("NAMA_DEPAN")
            editor.remove("NAMA_BELAKANG")
            editor.remove("EMAIL")
            editor.apply()

            val action = ProfileFragmentDirections.actionLoginFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

}