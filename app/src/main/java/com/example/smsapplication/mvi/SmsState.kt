package com.example.smsapplication.mvi

import com.example.smsapplication.data.requestModel.SendInfoRequestModel

sealed class SmsState {

    data class VerifyLogin(
        val response: SendInfoRequestModel?,
        val errorMessage: String?
    ) : SmsState()

    object Idle : SmsState()
}