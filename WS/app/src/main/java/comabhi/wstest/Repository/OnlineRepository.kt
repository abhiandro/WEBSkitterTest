package comabhi.wstest.Repository

import androidx.lifecycle.MutableLiveData
import com.eiipl.livestock.Network.RestManager
import comabhi.wstest.Model.RequestModel.LoginRequestData
import comabhi.wstest.Model.ResponseModel.LoginResponseData
import comabhi.wstest.Model.ResponseModel.UsersResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OnlineRepository{

    companion object{

        var loginResponseMutableLiveData : MutableLiveData<LoginResponseData> = MutableLiveData()

        var usersListMutableLiveData : MutableLiveData<UsersResponseData> = MutableLiveData()

        fun LoginCall( email : String , password : String) : MutableLiveData<LoginResponseData>{

            var loginRequestData : LoginRequestData = LoginRequestData(email,password)

            var loginacall : Call<LoginResponseData> = RestManager.getService()!!.LogInApiCall(loginRequestData = loginRequestData)
            loginacall.enqueue(object : Callback<LoginResponseData>{
                override fun onResponse(
                    call: Call<LoginResponseData>,
                    response: Response<LoginResponseData>
                ) {

                    if(response.code()==200){
                        loginResponseMutableLiveData.postValue(response.body())
                    }


                }

                override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {

                    loginResponseMutableLiveData.postValue(null)
                }
            })


            return  loginResponseMutableLiveData
        }

        fun  UsersListCall(page : Int) : MutableLiveData<UsersResponseData>{

            var usersListCall : Call<UsersResponseData> = RestManager.getService()!!.UsersApiCall(page)

            usersListCall.enqueue(object : Callback<UsersResponseData>{
                override fun onResponse(
                    call: Call<UsersResponseData>,
                    response: Response<UsersResponseData>
                ) {

                    if(response.code()==200){
                        usersListMutableLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UsersResponseData>, t: Throwable) {
                    usersListMutableLiveData.postValue(null)
                }
            })

            return usersListMutableLiveData
        }

    }

}