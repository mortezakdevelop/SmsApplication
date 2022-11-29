package com.example.smsapplication.repository

import androidx.lifecycle.LiveData
import com.example.smsapplication.api.ApiSuccessResponse
import com.example.smsapplication.api.GenericApiResponse
import com.example.smsapplication.api.MyRetrofitBuilder
import com.example.smsapplication.api.NetworkBoundResource
import com.example.smsapplication.data.requestModel.SendInfoRequestModel
import com.example.smsapplication.mvi.SmsState

object SmsRepository {
    fun sendSms(username:String,password:String,from_number:String,to_number:String,text:String): LiveData<SmsState> {
        return object : NetworkBoundResource<SendInfoRequestModel, SmsState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<SendInfoRequestModel>) {
                result.value =
                    SmsState.VerifyLogin(response.body, null)
            }

            override fun onReturnError(message: String) {
                result.value =
                    SmsState.VerifyLogin(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<SendInfoRequestModel>> {
                return MyRetrofitBuilder.apiService.getSms(
                    username,password,from_number,to_number,text
                )
            }

        }.asLiveData()
    }
}
