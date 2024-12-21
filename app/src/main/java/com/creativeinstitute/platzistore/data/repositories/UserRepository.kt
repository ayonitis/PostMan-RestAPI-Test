package com.creativeinstitute.platzistore.data.repositories

import com.creativeinstitute.platzistore.data.models.profile.ResponseProfile
import com.creativeinstitute.platzistore.services.UserService
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: UserService) {

    suspend fun profile() : Response<ResponseProfile>{
        return service.profile()
    }

}
