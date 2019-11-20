package com.marklynch.jsonclient.io

import com.marklynch.jsonclient.io.api.Server
import com.marklynch.jsonclient.io.api.response.ResponseObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Worker {

    private val server = Server()

    private val questionCallback = object : Callback<ResponseObject> {
        override fun onResponse(call: Call<ResponseObject>, response: Response<ResponseObject>) {
            if (!response.isSuccessful || response.body() == null) {
                //ERROR
                println("testQuestionCallback.onResponse ERROR")
                println("call.request().url() = " + call.request().url)
                println("response.code() = " + response.code())
            } else {
                //SUCCESS
                println("testQuestionCallback.onResponse SUCCESS")
                println("call.request().url() = " + call.request().url)
                println("response.code() = " + response.code())
                println("response.body() = " + response.body())
                val answer = doWork(response.body())
                server.callAnswerGetRequest(answer, testAnswerCallback)
                server.callAnswerPostRequest(answer, testAnswerCallback)
            }
        }

        override fun onFailure(call: Call<ResponseObject>, t: Throwable) {
            //FAILURE
            println("testQuestionCallback.onFailure ERROR")
            println("call.request().url() = " + call.request().url)
            t.printStackTrace()
        }
    }

    private val testAnswerCallback = object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if (!response.isSuccessful) {
                //ERROR
                println("testAnswerCallback.onResponse ERROR")
                println("call.request().url() = " + call.request().url)
                println("call.request().body() = " + call.request().body!!)
                println("response.code() = " + response.code())
                println("response.body() = " + response.body())
            } else {
                //SUCCESS
                println("testAnswerCallback.onResponse SUCCESS")
                println("call.request().url() = " + call.request().url)
                println("call.request().body() = " + call.request().body!!)
                println("response.code() = " + response.code())
                println("response.body() = " + response.body()!!)
            }
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            //FAILURE
            println("testAnswerCallback.onFailure ERROR")
            println("call.request().url() = " + call.request().url)
            t.printStackTrace()
        }
    }

    fun go() {
        server.callQuestionRequest(questionCallback)
    }

    private fun doWork(response: ResponseObject?): String {
        val answer = response.toString()
        println("doWork answer = $answer")
        return answer
    }

}
