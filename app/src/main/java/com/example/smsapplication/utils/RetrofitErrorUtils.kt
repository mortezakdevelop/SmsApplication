package com.example.smsapplication.utils

import com.example.smsapplication.api.ApiError
import com.google.gson.Gson

class RetrofitErrorUtils {
    companion object{
        fun parseError(response: String): String? {
            val gson = Gson()
            var apiError = ApiError()
            try {
                apiError =
                    gson.fromJson(response, ApiError::class.java)
            }catch (e:Exception){
                val hashMap= hashMapOf<String , ArrayList<String>>()
                apiError.error = hashMap
            }

            return apiError.getError()
        }

    }
}