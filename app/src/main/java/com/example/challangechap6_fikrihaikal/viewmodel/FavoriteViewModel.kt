package com.example.challangechap6_fikrihaikal.viewmodel

import androidx.lifecycle.*
import com.example.challangechap6_fikrihaikal.model.data.local.entity.FavoriteEntity

import com.example.challangechap6_fikrihaikal.model.data.repository.LocalRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {

    private val _favorite = MutableLiveData<List<FavoriteEntity>>()
    val favorite: LiveData<List<FavoriteEntity>> = _favorite

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user

    fun session() {
        if (Firebase.auth.currentUser != null) {
            _user.postValue(Firebase.auth.currentUser)
        } else {
            _user.postValue(null)
        }
    }

    fun getAllFavorites(uuid: String) = viewModelScope.launch {
        _favorite.postValue(localRepository.getFavoriteTickets(uuid))
    }
}
