package com.capstone.rasain.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val user: User
)

data class User(

	@field:SerializedName("fullName")
	val fullName: String,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("email")
	val email: String
)

data class Data(

	@field:SerializedName("user")
	val data: Data?,

	@field:SerializedName("token")
	val token: String
)
