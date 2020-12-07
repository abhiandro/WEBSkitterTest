package comabhi.wstest.Model.ResponseModel

import com.google.gson.annotations.SerializedName

class UsersListResponse {

    @SerializedName("avatar")
    var avatar : String ? = null

    @SerializedName("email")
    var email : String ? = null

    @SerializedName("first_name")
    var first_name : String ? = null

    @SerializedName("id")
    var id : String ? = null

    @SerializedName("last_name")
        var last_name : String ? = null

}