package br.com.fiap.easyfin.components

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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.easyfin.ui.theme.AccentGreen
import br.com.fiap.easyfin.ui.theme.AccentRed
import br.com.fiap.easyfin.ui.theme.PrimaryBlue
import br.com.fiap.easyfin.ui.theme.PrimaryDark
import br.com.fiap.easyfin.ui.theme.PrimaryLight

@Composable
fun BalanceCard(
    modifier: Modifier = Modifier, 
    balance: Double,
    onOptionsClick: () -> Unit = {}
) {
    val cornerRadius = 24.dp
    val isPositiveBalance = balance >= 0
    
    // Define gradient colors based on balance
    val gradientColors = if (isPositiveBalance) {
        listOf(PrimaryBlue, PrimaryLight)
    } else {
        listOf(PrimaryDark, PrimaryBlue)
    }
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = gradientColors
                    )
                )
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Saldo Disponível",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "R$ %.2f".format(balance),
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Status indicator with balance trend
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // Circle indicator
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isPositiveBalance) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = if (isPositiveBalance) "Saldo Positivo" else "Saldo Negativo",
                                tint = if (isPositiveBalance) AccentGreen else AccentRed,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.width(4.dp))
                        
                        Text(
                            text = if (isPositiveBalance) "Positivo" else "Negativo",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }
                }
                
                // Menu button
                IconButton(
                    onClick = onOptionsClick,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.1f))
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Opções",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun BalanceCardView(modifier: Modifier = Modifier) {
    BalanceCard(balance = 1000.0)
}

@Preview
@Composable
fun NegativeBalanceCardView(modifier: Modifier = Modifier) {
    BalanceCard(balance = -500.0)
}