package comabhi.wstest.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import comabhi.wstest.Model.ResponseModel.LoginResponseData
import comabhi.wstest.Repository.OnlineRepository

class LoginViewModel  : ViewModel(){

    fun LogInApiCallVM(email : String , password: String):MutableLiveData<LoginResponseData>{

        return  OnlineRepository.LoginCall(email,password)
    }
}