package comabhi.wstest.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import comabhi.wstest.Model.ResponseModel.LoginResponseData
import comabhi.wstest.R
import comabhi.wstest.Utility.CommonFunctions
import comabhi.wstest.ViewModel.LoginViewModel
import comabhi.wstest.databinding.ActivityMainBinding
import io.paperdb.Paper

class LogInActivity : AppCompatActivity() {
    lateinit var  binding: ActivityMainBinding
    lateinit var  loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.button.setOnClickListener {

            if(binding.username.text.toString().isEmpty()){

                Toast.makeText(this,"Please Enter User Name",Toast.LENGTH_LONG).show()
            }
            else if(binding.password.text.toString().isEmpty()){
                Toast.makeText(this,"Please Enter Pass Word",Toast.LENGTH_LONG).show()
            }
            else{
                if(CommonFunctions.isNetworkConnected(this)) {
                    CallLogIn(binding.username.text.toString(), binding.password.text.toString())
                }
                else{
                    Toast.makeText(this,"Please Check Internet Connections",Toast.LENGTH_LONG).show()
                }
            }

        }



    }


    fun CallLogIn(  email : String , password : String ){

        loginViewModel.LogInApiCallVM(email,password).observe(this, Observer {

            if(it!=null){

                var loginResponseData : LoginResponseData = it
                Toast.makeText(this,""+loginResponseData.email,Toast.LENGTH_LONG).show()
                Toast.makeText(this,""+loginResponseData.password,Toast.LENGTH_LONG).show()
                Paper.book().write("USERNAME",""+loginResponseData.email)
                Paper.book().write("ID",""+loginResponseData.id)
                var intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)

            }

        })

    }

}