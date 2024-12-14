package com.poc.flowardapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.poc.flowardapp.model.Post
import com.poc.flowardapp.model.User
import com.poc.flowardapp.veiwModel.MainViewModel

@Composable
fun UserDetailScreen(user: User, viewModel: MainViewModel) {
    val posts = viewModel.getPostsByUserId(user.userId)

    Column(modifier = Modifier.padding(10.dp)) {
        Image(
            painter = rememberImagePainter(user.url),
            contentDescription = "User Avatar",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray)
                .border(0.1.dp, Color(0xFFCECECE))
        )
        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(posts) { post ->
                PostItem(post)
            }
        }
    }
}
@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.5.dp, Color(0xFFCECECE)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = post.title,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF000000)
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = post.body,
                fontWeight = FontWeight.Light,
                color = Color(0xFF545454),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
