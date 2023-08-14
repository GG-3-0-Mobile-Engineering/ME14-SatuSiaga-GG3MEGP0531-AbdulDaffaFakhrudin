package com.abduladf.satusiaga.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.abduladf.satusiaga.data.repository.MainRepositoryImpl
import com.abduladf.satusiaga.data.repository.UserRepositoryImpl
import com.abduladf.satusiaga.data.service.PetabencanaService
import com.abduladf.satusiaga.domain.repository.MainRepository
import com.abduladf.satusiaga.domain.repository.UserRepository
import com.abduladf.satusiaga.domain.usecase.MainUseCase
import com.abduladf.satusiaga.domain.usecase.UserPrefUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val USER_PREFS_NAME = "user_prefs"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(USER_PREFS_NAME)
        }
    }

    @Provides
    @Singleton
    fun provideUserRepository(dataStore: DataStore<Preferences>): UserRepository {
        return UserRepositoryImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideUserPrefUseCase(userRepository: UserRepository): UserPrefUseCase {
        return UserPrefUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideMainRepository(pbService: PetabencanaService): MainRepository {
        return MainRepositoryImpl(pbService)
    }

    @Provides
    @Singleton
    fun provideMainUseCase(mainRepository: MainRepository): MainUseCase {
        return MainUseCase(mainRepository)
    }

}