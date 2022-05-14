package com.capstone.rasain.response

import com.google.gson.annotations.SerializedName

data class NewRecipeResponse(

	@field:SerializedName("method")
	val method: String,

	@field:SerializedName("results")
	val results: ArrayList<ResultsItem>,

	@field:SerializedName("status")
	val status: Boolean
)

data class ResultsItem(

	@field:SerializedName("times")
	val times: String,

	@field:SerializedName("thumb")
	val thumb: String,

	@field:SerializedName("portion")
	val portion: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("key")
	val key: String,

	@field:SerializedName("dificulty")
	val dificulty: String
)
