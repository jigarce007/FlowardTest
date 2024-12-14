package com.poc.flowardapp.network

import com.poc.flowardapp.model.Post
import com.poc.flowardapp.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers(): List<User>  = apiService.getUsers()
    suspend fun getPosts(): List<Post> = apiService.getPosts()
}