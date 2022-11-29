package com.example.smsapplication.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getBoolean
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.smsapplication.R
import com.example.smsapplication.databinding.FragmentSplashScreenBinding
import java.lang.Boolean.getBoolean
import java.lang.reflect.Array.getBoolean


class SplashScreenFragment : Fragment() {

    private lateinit var fragmentSplashScreenBinding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSplashScreenBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_splash_screen,
            container,
            false
        )
        showSplashScreen()
        return fragmentSplashScreenBinding.root
    }

    private fun showSplashScreen() {
        Handler(Looper.myLooper()!!).postDelayed({

                Navigation.findNavController(fragmentSplashScreenBinding.root)
                    .navigate(R.id.action_splashScreenFragment_to_phoneNumberFragment)


        }, 4000)
    }

}
