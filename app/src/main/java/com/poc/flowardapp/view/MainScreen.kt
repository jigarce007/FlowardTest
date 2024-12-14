package com.poc.flowardapp.view

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.poc.flowardapp.model.User
import com.poc.flowardapp.veiwModel.MainViewModel
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {
    val users by viewModel.users.collectAsState()
    val posts by viewModel.posts.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(users) { user ->
            val userPosts = posts.filter { it.userId == user.userId }
            UserItem(user, userPosts.size) {
                navController.navigate("user/${user.userId}")
            }
        }
    }
}

@Composable
fun UserItem(user: User, postCount: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(onClick = onClick),
                shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.5.dp, Color(0xFFCECECE)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),

            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(user.thumbnailUrl),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = user.name ?: "No Title",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF000000)
                )
                Text(
                    text = "Posts($postCount)",
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF545454)
                )
            }
            IconButton(onClick = { /* Handle action */ }) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
            }
        }
    }
}
