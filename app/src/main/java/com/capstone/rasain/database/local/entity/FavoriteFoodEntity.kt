package com.capstone.rasain.database.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favoriteFood")
@Parcelize
data class FavoriteFoodEntity(

    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id")
    var id: Int,

    @field:ColumnInfo(name = "key")
    var key: String,

    @field:ColumnInfo(name = "title")
    var title: String,

    @field:ColumnInfo(name = "imgUrl")
    var imgUrl: String? = null

) : Parcelable