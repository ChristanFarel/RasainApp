package com.capstone.rasain.response

import com.google.gson.annotations.SerializedName

data class FoodPredictionResponse(

	@field:SerializedName("data")
	val data: DataPredict,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class DataPredict(

	@field:SerializedName("predictions")
	val predictions: ArrayList<PredictionsItem>
)

data class PredictionsItem(

	@field:SerializedName("score")
	val score: String,

	@field:SerializedName("label")
	val label: String
)
