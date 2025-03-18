package br.com.fiap.easyfin.view

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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.easyfin.R
import br.com.fiap.easyfin.util.UserPreferences

@Composable
fun OnboardingView(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var currentPage by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val userPreferences = UserPreferences.getInstance(context)
    
    val onboardingItems = listOf(
        OnboardingItem(
            title = "Bem-vindo ao EasyFin",
            description = "Gerencie suas finanças de forma simples e eficiente",
            imageResId = R.drawable.signup_banner // Using existing image as placeholder
        ),
        OnboardingItem(
            title = "Controle seus gastos",
            description = "Acompanhe suas despesas e saiba exatamente para onde seu dinheiro está indo",
            imageResId = R.drawable.signup_banner // Using existing image as placeholder
        ),
        OnboardingItem(
            title = "Planeje seu futuro",
            description = "Estabeleça metas financeiras e acompanhe seu progresso facilmente",
            imageResId = R.drawable.signup_banner // Using existing image as placeholder
        )
    )
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            
            // Display current onboarding item
            Image(
                painter = painterResource(id = onboardingItems[currentPage].imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = onboardingItems[currentPage].title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = onboardingItems[currentPage].description,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Indicators
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                repeat(onboardingItems.size) { index ->
                    val isSelected = index == currentPage
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (isSelected) 12.dp else 10.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) MaterialTheme.colorScheme.primary 
                                else MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                            )
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Navigation buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (currentPage > 0) {
                    Button(
                        onClick = { currentPage-- },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                    ) {
                        Text(text = "Anterior", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
                
                Button(
                    onClick = {
                        if (currentPage < onboardingItems.size - 1) {
                            currentPage++
                        } else {
                            // Mark onboarding as complete
                            userPreferences.setOnboardingComplete(true)
                            
                            // Navigate to home when finished
                            navController.navigate("home") {
                                popUpTo("onboarding") {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                ) {
                    Text(
                        text = if (currentPage < onboardingItems.size - 1) "Próximo" else "Começar",
                        fontSize = 18.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
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