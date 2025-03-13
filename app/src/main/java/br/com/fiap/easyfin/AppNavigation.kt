package br.com.fiap.easyfin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.easyfin.view.AuthView
import br.com.fiap.easyfin.view.HomeView
import br.com.fiap.easyfin.view.LoginView
import br.com.fiap.easyfin.view.SignupView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val isLoggedIn = Firebase.auth.currentUser != null
    val firstPage = if(isLoggedIn) "home" else "auth"

    NavHost(navController = navController, startDestination = firstPage) {
        composable("auth") {
            AuthView(modifier, navController)
        }

        composable("login") {
            LoginView(modifier, navController)
        }

        composable("signup") {
            SignupView(modifier, navController)
        }

        composable("home") {
            HomeView(modifier, navController)
        }
    }
}