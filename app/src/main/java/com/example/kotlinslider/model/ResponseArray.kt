package com.example.kotlinslider.model

data class ResponseArray(
    val android_app_link: String,
    val android_app_version: String,
    val banner_images: ArrayList<String>,
    val ios_app_link: String,
    val ios_app_version: String
)