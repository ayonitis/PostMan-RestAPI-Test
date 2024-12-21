package com.creativeinstitute.platzistore.upload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativeinstitute.platzistore.data.models.profile.ResponseProfile
import com.creativeinstitute.platzistore.data.repositories.UploadRepository
import com.creativeinstitute.platzistore.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(private val repository: UploadRepository) :ViewModel() {

    private val _response = MutableLiveData<Response<ResponseUpload>>()
    val uploadResponse:LiveData<Response<ResponseUpload>> = _response

    fun upload(file: MultipartBody.Part) {

        viewModelScope.launch {

            _response.postValue(repository.upload(file))
        }
    }

}