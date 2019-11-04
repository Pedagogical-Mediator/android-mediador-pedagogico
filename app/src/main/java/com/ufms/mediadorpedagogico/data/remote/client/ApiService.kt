package com.ufms.mediadorpedagogico.data.remote.client

import com.ufms.mediadorpedagogico.data.remote.entity.*
import com.ufms.mediadorpedagogico.data.remote.entity.homework.ApiHomeworkContent
import com.ufms.mediadorpedagogico.data.remote.entity.library.ApiLibContent
import com.ufms.mediadorpedagogico.data.remote.entity.library.ApiLibResource
import com.ufms.mediadorpedagogico.data.remote.entity.library.ApiTopic
import com.ufms.mediadorpedagogico.data.remote.entity.news.ApiNewsContent
import com.ufms.mediadorpedagogico.data.remote.entity.notice.ApiNoticeContent
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("alunos")
    @FormUrlEncoded
    fun signIn(
        @Field("chaveDeAcesso") classKey: String,
        @Field("nome") name: String
    ): Single<Response<ApiUser>>

    /**
     * Homework
     * */

    @POST("aulas/")
    @FormUrlEncoded
    fun getListOfHomework(@Field("page") pageNumber: Int, @Field("chaveDeAcesso") classKey: String): Single<Response<ApiHomeworkContent>>

    /**
     * Notice
     * */

    @GET("avisos")
    fun getListOfNotices(@Query("page") pageNumber: Int): Single<Response<ApiNoticeContent>>

    /**
     * Notice
     * */

    @GET("noticias")
    fun getListOfNews(@Query("page") pageNumber: Int): Single<Response<ApiNewsContent>>

    /**
     * Bullying
     * */

    @GET("bullying")
    fun getBullyingInformation(): Single<Response<ApiBullying>>

    /**
     * Guild
     * */

    @GET("gremio")
    fun getGuildInformation(): Single<Response<ApiGuild>>

    /**
     * About
     * */

    @GET("escola")
    fun getAboutInformation(): Single<Response<ApiAbout>>

    /**
     * Calendar
     * */

    @GET("calendario")
    fun getCalendar(): Single<Response<ApiCalendar>>


    /**
     * Library
     * */

    @GET("topicos")
    fun getTopics(): Single<Response<List<ApiTopic>>>

    @GET("itens")
    fun getLibResources(@Query("page") pageNumber: Int, @Query("idDoTopico") topicId: Int): Single<Response<ApiLibContent>>
}
