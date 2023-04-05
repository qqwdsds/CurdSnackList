package com.example.curdsnacklist.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.net.URI

@Parcelize
data class CurdSnack(val species: String,
                     val fats: String,
                     val weight: String,
                     val amount: String,
                     val type_of_packaging: String,
                     val storage_conditions: String,
                     val expiration_date: String,
                     val bar_code: String,
                     val image: String) : Parcelable