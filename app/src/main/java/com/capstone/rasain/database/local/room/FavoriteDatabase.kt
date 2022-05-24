package com.capstone.rasain.database.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capstone.rasain.database.local.entity.FavoriteFoodEntity

@Database(
    entities =[FavoriteFoodEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favDao(): FavoriteDao

    companion object {
        @Volatile
        private var instance: FavoriteDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteDatabase {
            if (instance == null) {
                synchronized(FavoriteDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java, "fav_database"
                    )
                        .build()
                    1       }
            }
            return instance as FavoriteDatabase
        }

    }
}