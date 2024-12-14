package com.poc.flowardapp.veiwModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.flowardapp.model.Post
import com.poc.flowardapp.model.User
import com.poc.flowardapp.network.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: MutableStateFlow<List<User>> get() = _users

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: MutableStateFlow<List<Post>> get() = _posts

    init {
        viewModelScope.launch {
            _users.value = userRepository.getUsers()
            _posts.value = userRepository.getPosts()
        }
    }

    fun getPostsByUserId(userId: Int): List<Post> {
        return posts.value.filter { it.userId == userId }
    }
}