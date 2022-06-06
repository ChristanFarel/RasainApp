package com.capstone.rasain.database.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.capstone.rasain.database.local.entity.FavoriteFoodEntity


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFav(fav: FavoriteFoodEntity)

    @Update
    fun updateFav(fav: FavoriteFoodEntity)

    @Query("DELETE FROM favoriteFood WHERE [key] = :key")
    fun deleteFav(key: String)

    @Query("SELECT * FROM favoriteFood ORDER BY id")
    fun getFav(): LiveData<List<FavoriteFoodEntity>>

    @Query("SELECT EXISTS(SELECT title FROM favoriteFood WHERE title = :title)")
    fun checkTitle(title: String): LiveData<Boolean>

    @Query("SELECT EXISTS(SELECT * FROM favoriteFood WHERE [key] = :key)")
    fun checkKey(key: String): LiveData<Boolean>
}