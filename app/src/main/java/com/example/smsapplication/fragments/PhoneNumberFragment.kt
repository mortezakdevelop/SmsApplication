package com.example.smsapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.smsapplication.R
import com.example.smsapplication.databinding.FragmentPhoneNumberBinding
import com.example.smsapplication.mvi.SmsState
import com.example.smsapplication.utils.PHONE_NUMBER
import com.example.smsapplication.viewmodel.SmsViewModel


open class PhoneNumberFragment : Fragment() {

    private val viewModel: SmsViewModel by viewModels()
    private lateinit var fragmentPhoneNumberBinding: FragmentPhoneNumberBinding

    // create random code
    private val randomCode = (1000..9999).shuffled().last().toString()

    private val senderCode = -1
    private val userName = "pariinaz"
    private val password = "john&rocky!"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentPhoneNumberBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_phone_number,
            container,
            false
        )
        fragmentPhoneNumberBinding.btnInput.setOnClickListener {
            checkPhoneNumber()
        }

        return fragmentPhoneNumberBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }

    private fun checkPhoneNumber() {

        val phoneNumber = fragmentPhoneNumberBinding.etPhoneNumber.text
        val fullNumber = fragmentPhoneNumberBinding.etPhoneNumber.text.toString()
        if (phoneNumber.length >= 4) {
            val firstDigit = phoneNumber.toString().substring(1, 4).toInt()

            if (phoneNumber.length != 11) {
                Toast.makeText(requireContext(), "شماره موبایل اشتباه است", Toast.LENGTH_LONG)
                    .show()
            } else if (firstDigit in 911..918) {
                val bundle = Bundle()
                bundle.putString(PHONE_NUMBER, fullNumber)
                viewModel.addUserName(
                    userName,
                    password,
                    senderCode.toString(),
                    fullNumber,
                    randomCode
                )
                findNavController().navigate(
                    R.id.action_phoneNumberFragment_to_verificationCodeFragment,
                    bundle
                )
                // sendSMS()

            } else if (firstDigit > 919) {
                Toast.makeText(requireContext(), "شماره موبایل اشتباه است", Toast.LENGTH_LONG)
                    .show()
            }else if (firstDigit<902) {
                Toast.makeText(requireContext(), "شماره موبایل اشتباه است", Toast.LENGTH_LONG)
                    .show()

            }else if (phoneNumber.length<4){
                Toast.makeText(requireContext(),"شماره موبایل اشتباه است",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "شماره موبایل اشتباه است", Toast.LENGTH_LONG).show()
            }
        }else if(phoneNumber.isEmpty()){
            Toast.makeText(requireContext(), "لطفا شماره موبایل خود را وارد کنید", Toast.LENGTH_LONG).show()

        }else if (phoneNumber.length<4){
            Toast.makeText(requireContext(),"شماره موبایل اشتباه است",Toast.LENGTH_LONG).show()
        }
    }

    private fun subscribeObserver() {
        viewModel.dataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is SmsState.VerifyLogin -> {
                    // hideProgress()
                    dataState.errorMessage?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                    }
                    dataState.response?.let {
                        viewModel.addUserName(it.user_name,it.password!!,it.from_number!!,it.to_number!!,it.text!!)

                    }
                }

            }
        }

        viewModel.stateOff()
    }
}


//    private fun sendSMS():String {
//        try {
//            val userName = "userName" + "pariinaz"
//            val password = "password" + "john&rocky!"
//            val message = " Your verification code is $randomCode"
//            val sender = "sender is $senderCode"
//            val receiveCode = "09398492959"
//
//            val policy:StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//            StrictMode.setThreadPolicy(policy)
//            val connection: HttpURLConnection =
//                URL("https://www.payam-resan.com/ws/v2/ws.asmx?op").openConnection() as HttpURLConnection
//            val data = userName + password + receiveCode + message + sender
//            connection.doOutput = true
//            connection.requestMethod = "POST"
//            connection.setRequestProperty("Content-Length", data.length.toString())
//            connection.outputStream.write(data.toByteArray())
//            val rd = BufferedReader(InputStreamReader(connection.inputStream))
//            val stringBuffer = StringBuffer()
//            val line = rd.readLine()
//            while (line != null) {
//                stringBuffer.append()
//            }
//            rd.close()
//            Toast.makeText(requireContext(),"کد با موقیت ارسال شد.",Toast.LENGTH_LONG).show()
//
//            return stringBuffer.toString()
//        } catch (e: java.lang.Exception) {
//            print("Error SMS + $e")
//            return "Error SMS$e"
//        }
//    }


//    // give otp sms code
//    private fun giveVerificationCode(sender:Int){
//        var connection:HttpURLConnection()
//
//    }
//        private void sendMessage_Click(object sender, EventArgs e)
//        {
//            SmsV2 smswebservice = new SmsV2();
//            string[] RecipientNumbers = new string[100];
//            RecipientNumbers = txtNumber.Text.Split(',').ToArray();
//            long[] Result = new long[100];
//            int type = int.Parse(cboTypeofMsg.SelectedItem.ToString());
//            Result = smswebservice.SendMessage(
//                txtUserName.Text, txtPassword.Text,
//                txtMsgText.Text, RecipientNumbers,
//                txtSenderNumber.Text, type,
//                int.Parse(txtDelay.Text)
//            );
//            if (Result[0] > 0)
//            {
//                lblResult.Text = "SMS ID: " + Result[0];
//            }
//            else {
//                lblResult.Text = "Error: " + Result[0];
//            }
//        }
//    }
