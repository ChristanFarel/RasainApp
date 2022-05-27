package com.capstone.rasain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.capstone.rasain.response.Data
import com.capstone.rasain.response.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Preference private constructor(private val dataStore: DataStore<Preferences>) {


    fun getToken(): Flow<Data> {
        return dataStore.data.map { pref ->
            Data(
                null,
                pref[TOKEN_KEY] ?:""
            )
        }
    }

    suspend fun saveTokenUser(data: Data, user: User){
        dataStore.edit { preference ->
            preference[TOKEN_KEY] = data.token
//            preference[NAME_KEY] = user.fullName
//            preference[USER_ID] = user.userId
//            preference[EMAIL_KEY] = user.email
        }
    }

    fun getUser(): Flow<User>{
        return dataStore.data.map { pref ->
            User(
                pref[NAME_KEY] ?:"",
                pref[USER_ID] ?:"",
                pref[EMAIL_KEY] ?:""
            )
        }
    }






    companion object {
        @Volatile
        private var INSTANCE: Preference? = null

        private val USER = stringPreferencesKey("user")
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