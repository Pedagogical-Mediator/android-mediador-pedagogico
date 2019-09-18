package com.ufms.mediadorpedagogico.data.remote.client

import com.ufms.mediadorpedagogico.data.remote.entity.ApiUser
import com.ufms.mediadorpedagogico.data.remote.entity.homework.ApiHomeworkContent
import com.ufms.mediadorpedagogico.data.remote.entity.notice.ApiNoticeContent
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("TODO decidir rota") //TODO decidir rota
    fun signIn(
        @Field("chaveDeAcesso") classKey: String,
        @Field("nomeDoAluno") name: String
    ): Single<Response<ApiUser>>

    /**
     * Homework
     * */

    @GET("aulas")
    fun getListOfHomework(@Query("page") pageNumber: Int): Single<Response<ApiHomeworkContent>>


    /**
     * Notice
     * */

    @GET("avisos")
    fun getListOfNotices(@Query("page") pageNumber: Int): Single<Response<ApiNoticeContent>>

}
