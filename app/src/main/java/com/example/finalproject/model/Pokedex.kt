package com.example.finalproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Pokedex (
    @PrimaryKey
    var id: Int,
    var name: String,
    var type: String,
    var attack: Int,
    var height: Int,
    var weight: Int,
    var defense: Int,
    var imageUrl: String?,
    var description: String?

): Parcelable

//@Entity
//@Parcelize
//data class EvolutionChain(
//    @PrimaryKey
//    var id: String,
//    var name: String
//): Parcelable