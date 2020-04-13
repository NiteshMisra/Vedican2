package `in`.indilabz.vedican_parent.activity

import `in`.indilabz.vedican_parent.R
import `in`.indilabz.vedican_parent.databinding.ActivityOtpBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import `in`.indilabz.vedican_parent.extras.PrefData

class OTPActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOtpBinding
    private val key1 = "otp"
    private val key2 = "mobile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp)
        val otp : Int = intent.getIntExtra(key1,0)
        val mobileNo : String = intent.getStringExtra(key2)!!

        binding.loginBack.setOnClickListener {
            onBackPressed()
        }

        binding.otp.setText(otp.toString())

        binding.login.setOnClickListener {

            if (binding.otp.text.isEmpty()){
                Toast.makeText(this,"Enter OTP",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (binding.otp.text.toString().toInt() == otp){

                PrefData.session(true)
                PrefData.phone(mobileNo)
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

            }else{
                Toast.makeText(this,"Incorrect OTP",Toast.LENGTH_LONG).show()
            }

        }


    }
}
