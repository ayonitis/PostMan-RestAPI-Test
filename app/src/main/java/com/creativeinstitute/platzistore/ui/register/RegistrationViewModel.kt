package com.creativeinstitute.platzistore.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativeinstitute.platzistore.data.models.login.RequestLogin
import com.creativeinstitute.platzistore.data.models.login.ResponseLogin
import com.creativeinstitute.platzistore.data.models.register.RequestRegistration
import com.creativeinstitute.platzistore.data.models.register.ResponseRegistration
import com.creativeinstitute.platzistore.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repository: AuthRepository) :ViewModel() {

    private val _response = MutableLiveData<Response<ResponseRegistration>>()
    val registrationResponse:LiveData<Response<ResponseRegistration>> = _response


    fun register(request: RequestRegistration) {

        viewModelScope.launch {

            _response.postValue(repository.registration(request))
        }
    }

}