package com.bangkit.teras_app.data

import com.bangkit.teras_app.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow
import com.bangkit.teras_app.data.pref.UserModel as UserModel1

class TerasRepository private constructor(private val userPreference: UserPreference) {

    suspend fun saveSession(user: UserModel1) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel1> {
        return userPreference.getSession()
    }


    suspend fun logout() {
        userPreference.logout()
    }


    companion object {
        @Volatile
        private var instance: TerasRepository? = null

        fun getInstance(userPreference: UserPreference,): TerasRepository =
            instance ?: synchronized(this) {
                TerasRepository(userPreference).apply {
                    instance = this
                }
            }
    }
}