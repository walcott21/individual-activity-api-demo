package com.example.restapiidemo.home.data

import com.example.restapiidemo.home.data.PostModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("posts")
    fun fetchAllPosts(): Call<List<PostModel>>

    @POST("posts")
    fun createPost(@Body postModel: PostModel): Call<PostModel>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int): Call<Void>
}
