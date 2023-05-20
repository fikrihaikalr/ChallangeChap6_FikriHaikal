package com.example.challangechap6_fikrihaikal.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.challangechap6_fikrihaikal.R
import com.example.challangechap6_fikrihaikal.databinding.FragmentProfileBinding
import com.example.challangechap6_fikrihaikal.viewmodel.ProfileViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding
    private val viewModel : ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.session()
        viewModel.user.observe(viewLifecycleOwner){
            binding.apply {
                etEmail.setText(it?.email.toString())
            }
        }
        binding.apply {
            btnUpdate.setOnClickListener {
                val email = etEmail.text.toString().trim()
                viewModel.updateEmail(email)
                viewModel.update.observe(viewLifecycleOwner){
                    Toast.makeText(requireContext(),it, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.profileFragment)
                }
            }
            btnLogout.setOnClickListener {
                Firebase.auth.signOut()
                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
            }
        }
    }


}