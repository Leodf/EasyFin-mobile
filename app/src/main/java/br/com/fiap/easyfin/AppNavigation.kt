package br.com.fiap.easyfin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.easyfin.util.UserPreferences
import br.com.fiap.easyfin.view.AuthView
import br.com.fiap.easyfin.view.HomeView
import br.com.fiap.easyfin.view.LoginView
import br.com.fiap.easyfin.view.OnboardingView
import br.com.fiap.easyfin.view.SignupView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val userPreferences = UserPreferences.getInstance(context)

    // Check if user is already logged in
    val isLoggedIn = Firebase.auth.currentUser != null
    // Check if user has seen onboarding
    val hasSeenOnboarding = userPreferences.hasSeenOnboarding()
    
    // Choose starting destination based on login status
    val firstPage = if(isLoggedIn) {
        if (hasSeenOnboarding) "home" else "onboarding"
    } else {
        "auth"
    }

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

        composable("onboarding") {
            OnboardingView(modifier, navController)
        }

        composable("home") {
            HomeView(modifier, navController)
        }
    }
}