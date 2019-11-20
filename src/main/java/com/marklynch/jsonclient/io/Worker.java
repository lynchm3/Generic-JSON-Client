package com.marklynch.jsonclient.io;

import com.marklynch.jsonclient.KotlinWorker;
import com.marklynch.jsonclient.io.api.Server;
import com.marklynch.jsonclient.io.api.response.ResponseObject;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Worker {

    private final Server server = new Server();

    public void go() {
        server.callQuestionRequest(questionCallback);
    }

    private Callback<ResponseObject> questionCallback = new Callback<ResponseObject>() {
        @Override
        public void onResponse(@NotNull Call<ResponseObject> call, Response<ResponseObject> response) {
            if (!response.isSuccessful() || response.body() == null) {
                //ERROR
                System.out.println("testQuestionCallback.onResponse ERROR");
                System.out.println("call.request().url() = " + call.request().url());
                System.out.println("response.code() = " + response.code());
                System.out.println("response.body() = " + response.body());
            } else {
                //SUCCESS
                System.out.println("testQuestionCallback.onResponse SUCCESS");
                System.out.println("call.request().url() = " + call.request().url());
                System.out.println("response.code() = " + response.code());
                System.out.println("response.body() = " + response.body());
                String answer = doWork(response.body());
                server.callAnswerGetRequest(answer, testAnswerCallback);
                server.callAnswerPostRequest(answer, testAnswerCallback);
            }
        }

        @Override
        public void onFailure(@NotNull Call<ResponseObject> call, @NotNull Throwable t) {
            //FAILURE
            System.out.println("testQuestionCallback.onFailure ERROR");
            System.out.println("call.request().url() = " + call.request().url());
            t.printStackTrace();
        }
    };

    private Callback<Void> testAnswerCallback = new Callback<Void>() {
        @Override
        public void onResponse(@NotNull Call<Void> call, Response<Void> response) {
            if (!response.isSuccessful()) {
                //ERROR
                System.out.println("testAnswerCallback.onResponse ERROR");
                System.out.println("call.request().url() = " + call.request().url());
                System.out.println("call.request().body() = " + call.request().body());
                System.out.println("response.code() = " + response.code());
                System.out.println("response.body() = " + response.body());
            } else {
                //SUCCESS
                System.out.println("testAnswerCallback.onResponse SUCCESS");
                System.out.println("call.request().url() = " + call.request().url());
                System.out.println("call.request().body() = " + call.request().body());
                System.out.println("response.code() = " + response.code());
                System.out.println("response.body() = " + response.body());
            }
        }

        @Override
        public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
            //FAILURE
            System.out.println("testAnswerCallback.onFailure ERROR");
            System.out.println("call.request().url() = " + call.request().url());
            t.printStackTrace();
        }
    };

    private String doWork(ResponseObject response) {

        String answer = KotlinWorker.Companion.doWork(response);

        System.out.println("doWork answer = " + answer);
        return answer;
    }

}
