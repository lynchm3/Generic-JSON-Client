package com.marklynch.jsonclient.io.api

import com.marklynch.jsonclient.io.api.response.ResponseObject
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

class Server {

    private val apiService: APIService

    private val retrofitInstance: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

    private interface Fields {
        companion object {
            const val API_KEY = "api_key"
            const val ANSWER = "answer"
        }
    }

    init {
        this.apiService = retrofitInstance.create(APIService::class.java)
    }

    fun callQuestionRequest(callback: Callback<ResponseObject>) {
        apiService.questionRequest(ApiKey.API_KEY).enqueue(callback)
    }

    fun callAnswerGetRequest(answer: String, callback: Callback<Void>) {
        apiService.answerGetRequest(ApiKey.API_KEY, answer).enqueue(callback)
    }

    fun callAnswerPostRequest(answer: String, callback: Callback<Void>) {
        apiService.answerPostRequest(ApiKey.API_KEY, answer).enqueue(callback)
    }

    interface APIService {
        @GET(REST_API_QUESTION)
        fun questionRequest(@Query(Fields.API_KEY) apiKey: String): Call<ResponseObject>

        @GET(REST_API_ANSWER)
        fun answerGetRequest(
            @Query(Fields.API_KEY) apiKey: String,
            @Query(Fields.ANSWER) answer: String
        ): Call<Void>

        @POST(REST_API_ANSWER)
        fun answerPostRequest(
            @Query(Fields.API_KEY) apiKey: String,
            @Body text: String
        ): Call<Void>
    }

    companion object {
        private const val BASE_URL = "http://ip.jsontest.com/"
        private const val REST_API_QUESTION = "/"      //""/services/rest/question";
        private const val REST_API_ANSWER = "/services/rest/answer"
    }
}