package com.melvinbautista.exam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvinbautista.exam.constants.States
import com.melvinbautista.exam.model.Authentication
import com.melvinbautista.exam.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _authentication = MutableLiveData<Authentication?>()
    val authentication: LiveData<Authentication?> = _authentication

    fun register(username: String, password: String) {
        viewModelScope.launch {
            val success = userRepository.register(username, password)
            _authentication.value = Authentication(
                auth = if (success) States.Registered.SUCCESS else States.Registered.FAILED,
                username = username
            )
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val success = userRepository.login(username, password)
            _authentication.value = Authentication(
                auth = if (success) States.LoggedIn.SUCCESS else States.LoggedIn.FAILED,
                username = username
            )
        }
    }

    fun logout() {
        _authentication.value = null
    }
}
