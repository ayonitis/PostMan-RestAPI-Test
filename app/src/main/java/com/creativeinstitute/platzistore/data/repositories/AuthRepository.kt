package com.creativeinstitute.platzistore.data.repositories

import com.creativeinstitute.platzistore.data.models.login.RequestLogin
import com.creativeinstitute.platzistore.data.models.login.ResponseLogin
import com.creativeinstitute.platzistore.data.models.register.RequestRegistration
import com.creativeinstitute.platzistore.data.models.register.ResponseRegistration
import com.creativeinstitute.platzistore.services.AuthServices
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val service: AuthServices) {


    suspend fun login(requestLogin: RequestLogin) : Response<ResponseLogin>{

        return service.login(requestLogin)
    }


    suspend fun registration(request: RequestRegistration) : Response<ResponseRegistration>{

        return service.registration(request)
    }
}