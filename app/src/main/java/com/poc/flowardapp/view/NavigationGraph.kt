package com.poc.flowardapp.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.poc.flowardapp.veiwModel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavigationGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController, startDestination = "main") {
        composable("main") {
            MainScreen(viewModel, navController)
        }
        composable("user/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toInt()
            val user = viewModel.users.value.first { it.userId == userId }
            UserDetailScreen(user, viewModel)
        }
    }
}
