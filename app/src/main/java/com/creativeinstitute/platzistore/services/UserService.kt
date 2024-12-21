package com.creativeinstitute.platzistore.services

import com.creativeinstitute.platzistore.data.models.profile.ResponseProfile
import retrofit2.Response
import retrofit2.http.POST

interface UserService {

    @POST("auth/profile")
    suspend fun profile(): Response<ResponseProfile>

}