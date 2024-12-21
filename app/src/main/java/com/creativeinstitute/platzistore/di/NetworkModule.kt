package com.creativeinstitute.platzistore.di

import com.creativeinstitute.platzistore.services.AuthServices
import com.creativeinstitute.platzistore.services.UserService
import com.creativeinstitute.platzistore.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideAuthServices(retrofit: Retrofit.Builder): AuthServices {
        return retrofit.build().create(AuthServices::class.java)
    }


    @Provides
    @Singleton
    fun provideHTTPClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor = authInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit.Builder, client: OkHttpClient): UserService {
        return retrofit.client(client).build().create(UserService::class.java)

    }




}