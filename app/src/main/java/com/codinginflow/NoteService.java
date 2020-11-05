package com.codinginflow;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoteService {

    private static final String BASE_URL = "http://architecture-example.mdmodas.com/api/";

    private NoteResource noteResource;

    public NoteService() {
        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request newRequest = originalRequest.newBuilder()
                                .header("Authorization",
                                        String.format("Bearer %s", "MY_TOKEN"))
                                .build();

                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        noteResource = retrofit.create(NoteResource.class);
    }

    public LiveData<List<Note>> get() {
        Call<List<Note>> call = noteResource.get();

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                if (!response.isSuccessful()) {
                    //textViewResult.setText("HTTP CODE: " + response.code());
                    return;
                }

                LiveData<List<Note>> notes = (LiveData<List<Note>>) response.body();

                return;
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
            }
        });
    }
}
