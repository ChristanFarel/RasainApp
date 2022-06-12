package com.capstone.rasain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.capstone.rasain.response.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Preference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getTokenUser(): Flow<User> {
        return dataStore.data.map { pref ->
            User(
                pref[NAME_KEY] ?:"",
                pref[USER_ID] ?:"",
                pref[EMAIL_KEY] ?:"",
                pref[TOKEN_KEY] ?:""
            )
        }
    }

    suspend fun saveTokenUser(user: User){
        dataStore.edit { preference ->
            preference[TOKEN_KEY] = user.token
            preference[NAME_KEY] = user.fullName
            preference[USER_ID] = user.userId
            preference[EMAIL_KEY] = user.email
        }
    }

    suspend fun logout(){
        dataStore.edit { pref ->
            pref[TOKEN_KEY] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: Preference? = null

        private val TOKEN_KEY = stringPreferencesKey("token")
        private val USER_ID = stringPreferencesKey("userId")
        private val NAME_KEY = stringPreferencesKey("name")
        private val EMAIL_KEY = stringPreferencesKey("email")

        fun getInstance(dataStore: DataStore<Preferences>): Preference {
            return INSTANCE ?: synchronized(this) {
                val instance = Preference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}