package com.dicoding.model.localstorage

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_user")
data class FavoriteUser(
    val login: String?,
    @PrimaryKey
    val id: Int,
    val avatar_url: String?,
    val html_url: String?
) : Parcelable
