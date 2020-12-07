package comabhi.wstest.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import comabhi.wstest.Model.ResponseModel.UsersResponseData
import comabhi.wstest.Repository.OnlineRepository

class UsersViewModel : ViewModel() {

    fun  UserListVmCall(page: Int) : MutableLiveData<UsersResponseData>{

        return  OnlineRepository.UsersListCall(page)
    }

}