package com.example.healthdiet.classes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Diet (
    val name: String = "",
    var greenList: MutableList<Product>,
    var redList: MutableList<Product>
) : Parcelable

