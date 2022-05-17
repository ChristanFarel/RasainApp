package com.capstone.rasain.response

import com.google.gson.annotations.SerializedName

data class DetailRecipeResponse(

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("results")
	val results: Results? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class NeedItemItem(

	@field:SerializedName("thumb_item")
	val thumbItem: String? = null,

	@field:SerializedName("item_name")
	val itemName: String? = null
)

data class Results(

	@field:SerializedName("servings")
	val servings: String? = null,

	@field:SerializedName("times")
	val times: String? = null,

	@field:SerializedName("ingredient")
	val ingredient: ArrayList<String?>? = null,

	@field:SerializedName("thumb")
	val thumb: Any? = null,

	@field:SerializedName("author")
	val author: Author? = null,

	@field:SerializedName("step")
	val step: ArrayList<String?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("needItem")
	val needItem: ArrayList<NeedItemItem?>? = null,

	@field:SerializedName("dificulty")
	val dificulty: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
)

data class Author(

	@field:SerializedName("datePublished")
	val datePublished: String? = null,

	@field:SerializedName("user")
	val user: String? = null
)
