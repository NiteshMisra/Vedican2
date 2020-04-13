@file:Suppress("DEPRECATION")

package `in`.indilabz.vedican_parent.activity

import `in`.indilabz.vedican_parent.R
import `in`.indilabz.vedican_parent.databinding.ActivityLoginBinding
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import `in`.indilabz.vedican_parent.extras.LoginInterface
import `in`.indilabz.vedican_parent.response.OtpResponse
import `in`.indilabz.vedican_parent.viewmodel.viewmodel
import dmax.dialog.SpotsDialog

class LoginActivity : AppCompatActivity(),LoginInterface {

    private lateinit var binding: ActivityLoginBinding
    private var otp: Int = 0
    private lateinit var viewModel: viewmodel
    private lateinit var dialog: AlertDialog
    private lateinit var mobileNo : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this@LoginActivity).get(viewmodel::class.java)
        dialog = SpotsDialog.Builder().setContext(this).build()

        binding.sendOtpButton.setOnClickListener {
            mobileNo = binding.mobile.text.toString()

            if (mobileNo.isEmpty() || mobileNo.length != 10) {
                Toast.makeText(this, "Enter correct mobile no.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            viewModel.sendOTP(mobileNo,this).observe(this,
                Observer<OtpResponse> {})
        }
    }

    override fun dismissDialog(error : String?, otpResponse: OtpResponse?) {
        dialog.dismiss()
        if (error != null){
            Toast.makeText(this@LoginActivity,error,Toast.LENGTH_LONG).show()
        }else{
            if (otpResponse != null){
                Toast.makeText(this,"OTP sent Successfully",Toast.LENGTH_LONG).show()
                val intent = Intent(this, OTPActivity::class.java)
                intent.putExtra("otp",otpResponse.result)
                intent.putExtra("mobile",mobileNo)
                startActivity(intent)
            }
        }
    }

    override fun showDialog() {
        dialog.setTitle("Please wait...")
        dialog.setCanceledOnTouchOutside(false)
        dialog.setMessage("Sending OTP...")
        dialog.show()
    }
}
