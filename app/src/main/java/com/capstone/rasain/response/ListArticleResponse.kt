package com.capstone.rasain.response

import com.google.gson.annotations.SerializedName

data class ListArticleResponse(

	@field:SerializedName("method")
	val method: String,

	@field:SerializedName("results")
	val results: ArrayList<ResultsItemArticle>,

	@field:SerializedName("status")
	val status: Boolean
)

data class ResultsItemArticle(

	@field:SerializedName("key")
	val key: String,

	@field:SerializedName("tags")
	val tags: String
)
