package com.marklynch.jsonclient.io.api;

import com.marklynch.jsonclient.io.api.response.ResponseObject;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class Server {

    private interface Fields {
        String API_KEY = "api_key";
        String ANSWER = "answer";
    }

    private final APIService apiService;
    private static final String BASE_URL = "http://ip.jsontest.com/";
    private static final String REST_API_QUESTION = "/";//""/services/rest/question";
    private static final String REST_API_ANSWER = "/services/rest/answer";

    public Server() {
        this.apiService = getRetrofitInstance().create(APIService.class);
    }

    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Server.BASE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public void callQuestionRequest(Callback<ResponseObject> callback) {
        apiService.questionRequest(ApiKey.API_KEY).enqueue(callback);
    }

    public void callAnswerGetRequest(String answer, Callback<Void> callback) {
        apiService.answerGetRequest(ApiKey.API_KEY, answer).enqueue(callback);
    }

    public void callAnswerPostRequest(String answer, Callback<Void> callback) {
        apiService.answerPostRequest(ApiKey.API_KEY, answer).enqueue(callback);
    }

    public interface APIService {
        @GET(REST_API_QUESTION)
        Call<ResponseObject> questionRequest(@Query(Fields.API_KEY) String apiKey);

        @GET(REST_API_ANSWER)
        Call<Void> answerGetRequest(@Query(Fields.API_KEY) String apiKey,
                                    @Query(Fields.ANSWER) String answer);

        @POST(REST_API_ANSWER)
        Call<Void> answerPostRequest(@Query(Fields.API_KEY) String apiKey,
                                     @Body String text);
    }
}