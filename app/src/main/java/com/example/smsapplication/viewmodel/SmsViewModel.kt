package com.example.smsapplication.viewmodel

import android.provider.Telephony.Sms
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.smsapplication.mvi.SmsState
import com.example.smsapplication.mvi.intent.SmsIntent
import com.example.smsapplication.repository.SmsRepository
import java.net.PasswordAuthentication

class SmsViewModel:ViewModel() {

    private val _userName: MutableLiveData<String> = MutableLiveData()
    val userName: LiveData<String> get() = _userName

    private val _password: MutableLiveData<String> = MutableLiveData()
    val password: LiveData<String> get() = _password

    private val _fromNumber: MutableLiveData<String> = MutableLiveData()
    val fromNumber: LiveData<String> get() = _fromNumber

    private val _toNumber: MutableLiveData<String> = MutableLiveData()
    val toNumber: LiveData<String> get() = _toNumber

    private val _text: MutableLiveData<String> = MutableLiveData()
    val text: LiveData<String> get() = _text

    private val _idleState = MutableLiveData<SmsState>(SmsState.Idle)
    private val _stateIntent: MutableLiveData<SmsIntent> = MutableLiveData()


    private fun handleStateEvent(stateIntent: SmsIntent): LiveData<SmsState> {
        return when (stateIntent) {
            is SmsIntent.UserName -> {
                SmsRepository.sendSms(stateIntent.from_number,stateIntent.to_number,stateIntent.userName,stateIntent.passWord,stateIntent.text)
            }

            is SmsIntent.Idle -> {
                _idleState
            }
        }
    }

    var dataState: LiveData<SmsState> = Transformations
        .switchMap(_stateIntent) { stateIntent ->
            stateIntent?.let {
                handleStateEvent(stateIntent)
            }
        }

    fun addUserName(userName: String,password:String,fromNumber:String,toNumber:String,text:String) {
        setStateEvent(SmsIntent.UserName(userName,password,fromNumber,toNumber,text))
    }

    private fun setStateEvent(intent: SmsIntent) {
        _stateIntent.value = intent
    }

    fun stateOff() {
        setStateEvent(SmsIntent.Idle)
    }
//    fun addPassword(password: String) {
//        setStateEvent(SmsIntent.Password(password))
//    }
//    fun addFromNumber(fromNumber: String) {
//        setStateEvent(SmsIntent.FromNumber(fromNumber))
//    }
//    fun addToNumber(toNumber: String) {
//        setStateEvent(SmsIntent.ToNumber(toNumber))
//    }
//    fun addText(text: String) {
//        setStateEvent(SmsIntent.Text(text))
//    }


}