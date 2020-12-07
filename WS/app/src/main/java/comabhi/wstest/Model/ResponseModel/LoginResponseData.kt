package comabhi.wstest.Model.ResponseModel

import com.google.gson.annotations.SerializedName

class LoginResponseData {

    /*{
    "email": "eve.w@reqres.in",
    "password": "cityslicka",
    "id": "775",
    "createdAt": "2020-12-06T16:50:31.518Z"
}*/
    @SerializedName("email")
    var email : String? = null

     @SerializedName("password")
    var password : String? = null

     @SerializedName("id")
    var id : String? = null

     @SerializedName("createdAt")
    var createdAt : String? = null


}