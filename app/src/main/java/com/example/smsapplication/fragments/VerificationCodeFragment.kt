package com.example.smsapplication.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smsapplication.R
import com.example.smsapplication.databinding.FragmentVerificationCodeBinding
import java.util.*


open class VerificationCodeFragment : Fragment() {

    private lateinit var timer: CountDownTimer

    lateinit var fragmentVerificationCodeBinding: FragmentVerificationCodeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentVerificationCodeBinding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_verification_code,container,false)

        if (arguments != null) {
            val fullNumber = requireArguments().getString("PHONE_NUMBER")
            fragmentVerificationCodeBinding.tvPhoneNumber.text = fullNumber
        }

        timer()
        return fragmentVerificationCodeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToHome()

        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        timer.start()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    private fun navigateToHome(){
        fragmentVerificationCodeBinding.btnInput.setOnClickListener {
            findNavController().navigate(R.id.action_verificationCodeFragment_to_homeFragment)
        }
    }

    private fun timer(){
         timer = object: CountDownTimer(30000,1000) {
             @SuppressLint("SetTextI18n")
             override fun onTick(millisUntilFinished: Long) {
                fragmentVerificationCodeBinding.tvTimer.text = "00:" + (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                fragmentVerificationCodeBinding.tvTimer.text = "دریافت مجدد کد"
                fragmentVerificationCodeBinding.tvTimer.setOnClickListener {
                    timer()
                }
            }
        }
        timer.start()
    }
}