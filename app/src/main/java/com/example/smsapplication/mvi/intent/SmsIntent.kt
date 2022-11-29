package com.example.smsapplication.mvi.intent

sealed class SmsIntent {

    data class UserName(val userName: String,val passWord: String,val from_number: String,val to_number: String,val text: String) : SmsIntent()
//    data class Password(val passWord: String) : SmsIntent()
//    data class FromNumber(val from_number: String) : SmsIntent()
//    data class ToNumber(val to_number: String) : SmsIntent()
//    data class Text(val text: String) : SmsIntent()

    object Idle : SmsIntent()

}