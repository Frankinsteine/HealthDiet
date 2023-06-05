package com.example.healthdiet.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeItem(
    var id: Int = 0,
    var title: String = "",
    var time: String = "",
    var energy: String = "",
    var desc: String = "",
    var image: String = "",
    var url: String = ""
):Parcelable