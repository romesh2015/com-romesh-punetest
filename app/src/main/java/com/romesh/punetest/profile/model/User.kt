package com.romesh.punetest.profile.model
data class User (
    val gender : String,
    val name : Name,
    val location : Location,
    val email : String,
    val username : String,
    val password : String,
    val salt : String,
    val md5 : String,
    val sha1 : String,
    val sha256 : String,
    val registered : Int,
    val dob : Int,
    val phone : String,
    val cell : String,
    val SSN : String,
    val picture : String
)
