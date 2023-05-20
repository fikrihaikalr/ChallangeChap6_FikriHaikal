package com.example.challangechap6_fikrihaikal.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangechap6_fikrihaikal.R
import com.example.challangechap6_fikrihaikal.databinding.FragmentHomeBinding
import com.example.challangechap6_fikrihaikal.view.adapter.HomeAdapter
import com.example.challangechap6_fikrihaikal.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var mainAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.session()
        initList()
        viewModel.getAllMoviesNowPlaying()
        viewModel.loadingState.observe(viewLifecycleOwner){
            binding.pb.isVisible = it
            binding.rvMovie.isVisible =!it
        }
        viewModel.movie.observe(viewLifecycleOwner) {
            mainAdapter.differ.submitList(it.results)
        }
        viewModel.user.observe(viewLifecycleOwner){
            binding.setName.text = "Hi, ${it?.email}"
        }
        binding.ivProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding.btnFavor.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }
    private fun initList() {
        mainAdapter = HomeAdapter()
        binding.rvMovie.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }


}