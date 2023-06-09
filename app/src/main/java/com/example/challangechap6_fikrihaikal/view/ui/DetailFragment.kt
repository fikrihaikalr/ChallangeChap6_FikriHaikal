package com.example.challangechap6_fikrihaikal.view.ui

import DetailMovieResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.challangechap6_fikrihaikal.R
import com.example.challangechap6_fikrihaikal.databinding.FragmentDetailBinding
import com.example.challangechap6_fikrihaikal.model.data.local.entity.FavoriteEntity
import com.example.challangechap6_fikrihaikal.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailFragment : Fragment() {

    lateinit var binding : FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var isFavorite by Delegates.notNull<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailMovie(arguments?.getInt("MOVIE_ID")!!)
        bind()
        viewModel.session()
        viewModel.isFavorite.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.btnFavor.setImageResource(R.drawable.ic_favorite2)
            } else {
                binding.btnFavor.setImageResource(R.drawable.ic_favorite)
            }
            isFavorite = it
        }

    }

    private fun selectFavorite(detailMovieResponse: DetailMovieResponse) {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                viewModel.isFavorite(detailMovieResponse.id, user.uid)
                binding.btnFavor.setOnClickListener {
                    if (!isFavorite) {
                        viewModel.addFavorite(
                            FavoriteEntity(
                                id_movie = detailMovieResponse.id,
                                photo = detailMovieResponse.posterPath,
                                title = detailMovieResponse.title,
                                uuid_user = user.uid
                            )
                        )
                        binding.btnFavor.setImageResource(R.drawable.ic_favorite2)
                        isFavorite = true
                    } else {
                        viewModel.deleteFavorite(detailMovieResponse.id, user.uid)
                        binding.btnFavor.setImageResource(R.drawable.ic_favorite)
                        isFavorite = false
                    }
                }
            }
        }

    }

    private fun bind() {
        viewModel.loadingState.observe(viewLifecycleOwner){
            binding.pb.isVisible = it
            binding.name.isVisible = !it
            binding.photo.isVisible = !it
            binding.backdrop.isVisible = !it
            binding.desc.isVisible = !it
        }

        viewModel.movie.observe(viewLifecycleOwner){
            val imageUrl = "https://image.tmdb.org/t/p/w500"
            Glide.with(requireContext()).load(imageUrl + it.backdropPath).into(binding.backdrop)
            Glide.with(requireContext()).load(imageUrl + it.posterPath).into(binding.photo)
            binding.name.text = it.title
            binding.desc.text = it.overview
            selectFavorite(it)
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }


}