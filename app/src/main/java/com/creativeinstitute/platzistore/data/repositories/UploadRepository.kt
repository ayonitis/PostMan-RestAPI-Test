package com.creativeinstitute.platzistore.data.repositories

import com.creativeinstitute.platzistore.data.models.login.RequestLogin
import com.creativeinstitute.platzistore.data.models.login.ResponseLogin
import com.creativeinstitute.platzistore.data.models.register.RequestRegistration
import com.creativeinstitute.platzistore.data.models.register.ResponseRegistration
import com.creativeinstitute.platzistore.services.AuthServices
import com.creativeinstitute.platzistore.services.UploadServices
import com.creativeinstitute.platzistore.upload.ResponseUpload
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class UploadRepository @Inject constructor(private val service: UploadServices) {


    suspend fun upload(file: MultipartBody.Part) : Response<ResponseUpload>{

        return service.upload(file)
    }

}