package com.capstone.rasain.response

import com.google.gson.annotations.SerializedName

data class DetailArticleResponse(

	@field:SerializedName("method")
	val method: String,

	@field:SerializedName("results")
	val results: ResultsDetailArticle,

	@field:SerializedName("status")
	val status: Boolean
)

data class ResultsDetailArticle(

	@field:SerializedName("thumb")
	val thumb: String,

	@field:SerializedName("date_published")
	val datePublished: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String
)
