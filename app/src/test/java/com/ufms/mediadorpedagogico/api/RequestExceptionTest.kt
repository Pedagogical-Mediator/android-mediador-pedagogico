package com.ufms.mediadorpedagogico.api

import com.ufms.mediadorpedagogico.data.remote.client.RequestException
import org.junit.Test
import java.io.IOException
import java.net.SocketTimeoutException

class RequestExceptionTest {

    @Test
    fun httpError() {
        val requestException = RequestException.httpError(401)
        assert(requestException.errorType == RequestException.ErrorType.HTTP)
    }

    @Test
    fun networkError() {
        val requestException = RequestException.networkError(IOException())
        assert(requestException.errorType == RequestException.ErrorType.NETWORK)
    }

    @Test
    fun timeoutError() {
        val requestException = RequestException.timeoutError(SocketTimeoutException())
        assert(requestException.errorType == RequestException.ErrorType.TIMEOUT)
    }

    @Test
    fun unexpectedError() {
        val requestException = RequestException.unexpectedError(Throwable())
        assert(requestException.errorType == RequestException.ErrorType.UNEXPECTED)
    }

    @Test
    fun isHttpError() {
        val requestException = RequestException.httpError(401)
        assert(requestException.isHttpError())
    }

    @Test
    fun isNetworkError() {
        val requestException = RequestException.networkError(IOException())
        assert(requestException.isNetworkError())
    }

    @Test
    fun isUnauthorizedError() {
        val requestException = RequestException.httpError(401)
        assert(requestException.isUnauthorizedError())
    }

    @Test
    fun isTimeOutException() {
        val requestException = RequestException.httpError(408)
        assert(requestException.isTimeOutException())
    }

    @Test
    fun isUnprocessableEntity() {
        val requestException = RequestException.httpError(422)
        assert(requestException.isUnprocessableEntity())
    }

    @Test
    fun getErrorForCodeExisting() {
        assert(RequestException.HttpError.getErrorForCode(400) == RequestException.HttpError.BAD_REQUEST)
    }

    @Test
    fun getErrorForCodeNotExisting() {
        assert(RequestException.HttpError.getErrorForCode(4000) == RequestException.HttpError.UNEXPECTED_ERROR)
    }
}
