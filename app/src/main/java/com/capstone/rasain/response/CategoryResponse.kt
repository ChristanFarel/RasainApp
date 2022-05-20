package com.capstone.rasain.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("results")
	val results: ArrayList<ResultsCategory>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class ResultsCategory(

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)
