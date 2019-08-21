package com.ufms.mediadorpedagogico.data.remote.client

import com.google.gson.Gson
import com.ufms.mediadorpedagogico.data.remote.entity.ApiError
import okhttp3.ResponseBody

object ApiErrorsFormatter {
    fun deserialize(responseBody: ResponseBody?): ApiError? {
        return Gson()
            .fromJson(responseBody?.string(), ApiError::class.java)
    }
}
