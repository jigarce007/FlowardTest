package com.poc.flowardapp.network

import com.poc.flowardapp.model.Post
import com.poc.flowardapp.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("users")
    suspend fun getUsers(): List<User>
}