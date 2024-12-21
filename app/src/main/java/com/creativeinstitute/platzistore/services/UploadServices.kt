package com.creativeinstitute.platzistore.services

import com.creativeinstitute.platzistore.upload.ResponseUpload
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadServices {

    @Multipart
    @POST("files/upload")
    suspend fun upload(@Part file: MultipartBody.Part): Response<ResponseUpload>

}