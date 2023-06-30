package com.example.healthdiet.classes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    var name: String,
    var code: String
):Parcelable