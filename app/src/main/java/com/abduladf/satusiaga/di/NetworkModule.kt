package com.abduladf.satusiaga.di

import com.abduladf.satusiaga.data.api.PetabencanaConstants.PB_BASE_URL
import com.abduladf.satusiaga.data.service.PetabencanaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideNetworkInterCeptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideNetworkClient(
        interceptor: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(PB_BASE_URL)
            .client(interceptor)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providePetabencanaService(
        retrofit: Retrofit
    ): PetabencanaService = retrofit.create(PetabencanaService::class.java)
}