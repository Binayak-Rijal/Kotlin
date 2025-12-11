package com.example.semesterthreeproject.repository

import com.example.semesterthreeproject.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface UserRepo {
//    {
//        "success":true,
    //    "message" : "login success"

//    }
    fun login(email: String,
              password: String,
              callback: (Boolean , String) -> Unit)

    fun register(
        email: String,
        password: String,
        callback: (Boolean, String , String) -> Unit
    )

    //     "success":true,
    //    "message" : "registration  success"

    fun addUserToDatabase(
        userId: String,
        model: UserModel,
        callback: (Boolean, String) -> Unit
    )

    fun updateProfile(
        userId: String,
        model: UserModel,
        callback: (Boolean, String) -> Unit
    )

    fun deleteAccount(userId: String, callback: (Boolean, String) -> Unit)

    fun getUserById(userId: String, callback: (Boolean, String, UserModel?) -> Unit)

    fun getAllUser(callback: (Boolean, String , List<UserModel>) -> Unit)

    fun getCurrentUser() : FirebaseUser?

    fun forgetPassword(email: String , callback: (Boolean, String) -> Unit)

    fun logOut(callback: (Boolean, String) -> Unit)
}