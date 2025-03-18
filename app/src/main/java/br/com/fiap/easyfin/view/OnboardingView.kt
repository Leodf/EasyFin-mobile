package br.com.fiap.easyfin.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.easyfin.R
import br.com.fiap.easyfin.ui.theme.PrimaryBlue
import br.com.fiap.easyfin.ui.theme.PrimaryLight
import br.com.fiap.easyfin.util.UserPreferences

@Composable
fun OnboardingView(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var currentPage by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val userPreferences = UserPreferences.getInstance(context)
    
    // Our onboarding content
    val onboardingItems = listOf(
        OnboardingItem(
            title = "Bem-vindo ao EasyFin",
            description = "Gerencie suas finanças de forma simples e eficiente com nossa interface intuitiva.",
            imageResId = R.drawable.signup_banner // Using existing image as placeholder
        ),
        OnboardingItem(
            title = "Controle seus gastos",
            description = "Acompanhe suas despesas em tempo real e saiba exatamente para onde seu dinheiro está indo.",
            imageResId = R.drawable.signup_banner // Using existing image as placeholder
        ),
        OnboardingItem(
            title = "Planeje seu futuro",
            description = "Estabeleça metas financeiras, monitore seu progresso e alcance seus objetivos com facilidade.",
            imageResId = R.drawable.signup_banner // Using existing image as placeholder
        )
    )
    
    // Main layout
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Background gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f)
                        )
                    )
                )
        )
        
        // Content column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
<<<<<<< HEAD
            // Top section with the content
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(700)) +
                        slideInHorizontally(
                            initialOffsetX = { it }, 
                            animationSpec = tween(700, easing = LinearOutSlowInEasing)
                        ),
                exit = fadeOut(animationSpec = tween(700)) +
                        slideOutHorizontally(
                            targetOffsetX = { -it },
                            animationSpec = tween(700, easing = FastOutSlowInEasing)
                        )
=======

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
>>>>>>> b75eec257ed8e82a4a71c2554f2b0a1f909be22b
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Image Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp),
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Image(
                            painter = painterResource(id = onboardingItems[currentPage].imageResId),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(40.dp))
                    
                    // Title
                    Text(
                        text = onboardingItems[currentPage].title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Description
                    Text(
                        text = onboardingItems[currentPage].description,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
            
<<<<<<< HEAD
            // Bottom section with indicators and buttons
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
=======
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
>>>>>>> b75eec257ed8e82a4a71c2554f2b0a1f909be22b
            ) {
                // Indicators
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth()
                ) {
                    repeat(onboardingItems.size) { index ->
                        val isSelected = index == currentPage
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .size(if (isSelected) 12.dp else 10.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isSelected) PrimaryBlue
                                    else MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                                )
                        )
                    }
                }
                
<<<<<<< HEAD
                // Navigation buttons
                if (currentPage == onboardingItems.size - 1) {
                    // Final screen - show the Get Started button
                    Button(
                        onClick = {
                            // Mark onboarding as complete
                            userPreferences.setOnboardingComplete(true)
                            
                            // Navigate to home
=======
                Button(
                    onClick = {
                        if (currentPage < onboardingItems.size - 1) {
                            currentPage++
                        } else {
                            userPreferences.setOnboardingComplete(true)
>>>>>>> b75eec257ed8e82a4a71c2554f2b0a1f909be22b
                            navController.navigate("home") {
                                popUpTo("onboarding") {
                                    inclusive = true
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Começar a usar",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Start",
                            tint = Color.White
                        )
                    }
                } else {
                    // Navigation buttons for other screens
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Back button - only shown if not on the first page
                        if (currentPage > 0) {
                            OutlinedButton(
                                onClick = { currentPage-- },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp),
                                shape = RoundedCornerShape(16.dp),
                                border = ButtonDefaults.outlinedButtonBorder.copy(
                                    width = 1.5.dp
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowLeft,
                                    contentDescription = "Back",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Anterior",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                        
                        // Next button or FloatingActionButton
                        if (currentPage == 0) {
                            // On first page, use full-width button
                            Button(
                                onClick = { currentPage++ },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(
                                    text = "Próximo",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = "Next"
                                )
                            }
                        } else {
                            // On other pages, use FAB
                            FloatingActionButton(
                                onClick = { currentPage++ },
                                containerColor = MaterialTheme.colorScheme.primary,
                                shape = CircleShape,
                                modifier = Modifier.size(56.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = "Next",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

data class OnboardingItem(
    val title: String,
    val description: String,
    val imageResId: Int
)

@Preview(showBackground = true, device = "id:pixel_5", showSystemUi = true)
@Composable
fun OnboardingViewPreview() {
    val navController = rememberNavController()
    OnboardingView(navController = navController)
} 