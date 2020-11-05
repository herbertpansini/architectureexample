package com.codinginflow;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NoteResource {

    @GET("note")
    Call<List<Note>> get();

    @POST("note")
    Call<Note> create(@Body Note note);

    @PUT("note/{id}")
    Call<Note> update(@Path("id") int id,
                       @Body Note note);

    @DELETE("note/{id}")
    Call<Void> delete(@Path("id") int id);
}
