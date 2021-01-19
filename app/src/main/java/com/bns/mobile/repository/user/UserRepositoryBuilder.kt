package com.bns.mobile.repository.user

import com.bns.mobile.domain.model.User
import com.bns.mobile.network.mapper.UserDtoMapper
import com.bns.mobile.network.model.user.UserDtoRequest
import com.bns.mobile.network.model.user.UserDtoResponse
import com.bns.mobile.network.services.UserService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryBuilder (
    private val service : UserService,
    private val mapper : UserDtoMapper
        ) : UserRepository {

    override suspend fun getUserInfo(request: UserDtoRequest, onResult: (User?) -> Unit) {
       service.getUserInfo(BaseApplication.sessionId, request).enqueue(
           object : Callback<UserDtoResponse> {
               override fun onFailure(call: Call<UserDtoResponse>, t: Throwable) {
                   t.printStackTrace()
                   println("error ${t.message}")
                   onResult(null)
               }

               override fun onResponse(call: Call<UserDtoResponse>, response: Response<UserDtoResponse>) {
                   var body: User? = null
                   val errorBody = response.errorBody()?.string()
                   body = if (response.body()?.responseCode == "00") {
                       mapper.mapToDomain(model = response.body())
                   } else {
                       Gson().fromJson(errorBody, User::class.java)
                   }
                   println("RESPONSE REPO:: ${response.body()}")
                   println("RESPONSE REPO ERROR:: $errorBody")
                   println("RESPONSE REPODD:: ${body.toString()}")
                   onResult(body)

               }
           }
       )
    }

}