package com.example.mycontacts.example.gson

import com.google.gson.annotations.SerializedName

data class ProfileModel(
    @SerializedName("dob")
    val dob: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("about")
    val about: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("company")
    val company: String,

    @SerializedName("location")
    val location: LocationModel
)
