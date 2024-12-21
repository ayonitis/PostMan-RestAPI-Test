package com.creativeinstitute.platzistore.services

import com.creativeinstitute.platzistore.data.models.login.RequestLogin
import com.creativeinstitute.platzistore.data.models.login.ResponseLogin
import com.creativeinstitute.platzistore.data.models.register.RequestRegistration
import com.creativeinstitute.platzistore.data.models.register.ResponseRegistration
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServices {


    @POST("auth/login")
    suspend fun login(@Body requestLogin: RequestLogin): Response<ResponseLogin>

    @POST("users/")
    suspend fun registration(@Body request: RequestRegistration): Response<ResponseRegistration>

}