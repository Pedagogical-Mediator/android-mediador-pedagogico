package com.ufms.mediadorpedagogico.data.remote.client

import com.ufms.mediadorpedagogico.data.remote.entity.ApiHomeworkContent
import com.ufms.mediadorpedagogico.data.remote.entity.ApiUser
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("users/sign_in")
    fun signIn(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("token") token: String?,
        @Field("platform") platform: String
    ): Single<Response<ApiUser>>

    @FormUrlEncoded
    @POST("auth/facebook")
    fun signInWithFacebook(@Field("access_token") accessToken: String): Single<Response<ApiUser>>

    @POST("users")
    fun signUp(@Body requestBody: RequestBody): Single<Response<ApiUser>>

    @FormUrlEncoded
    @POST("users/recover_password")
    fun sendPasswordRecovery(@Field("email") email: String): Single<Response<Void>>

    @GET("aulas")
    fun getListOfHomework(@Query("page") pageNumber: Int): Single<Response<ApiHomeworkContent>>

}
