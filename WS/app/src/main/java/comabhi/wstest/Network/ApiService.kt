package com.eiipl.livestock.Network


import comabhi.wstest.Model.RequestModel.LoginRequestData
import comabhi.wstest.Model.ResponseModel.LoginResponseData
import comabhi.wstest.Model.ResponseModel.UsersResponseData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST(Constant.Login)
    fun LogInApiCall(@Body loginRequestData: LoginRequestData) : Call<LoginResponseData>

    @GET(Constant.Users)
    fun UsersApiCall(@Query("page")  page : Int ) : Call<UsersResponseData>

}