package br.com.fiap.easyfin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.easyfin.model.TransactionModel
import br.com.fiap.easyfin.ui.theme.AccentGreen
import br.com.fiap.easyfin.ui.theme.AccentOrange
import br.com.fiap.easyfin.ui.theme.AccentRed
import br.com.fiap.easyfin.ui.theme.PrimaryLight
import br.com.fiap.easyfin.ui.theme.TextPrimary
import br.com.fiap.easyfin.ui.theme.TextSecondary

@Composable
fun TransactionCard(transaction: TransactionModel, onClick: () -> Unit) {
    // Determine if the transaction is income or expense based on the amount string
    val isIncome = !transaction.amount.contains("-")
    
    // Choose icon and color based on transaction type
    val (icon, iconBgColor) = getTransactionIconAndColor(transaction.title, isIncome)
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Transaction Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(iconBgColor.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = transaction.title,
                    tint = iconBgColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            // Transaction Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transaction.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = transaction.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
            
            // Amount
            Text(
                text = transaction.amount,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = if (isIncome) AccentGreen else AccentRed
            )
        }
    }
}

@Composable
private fun getTransactionIconAndColor(title: String, isIncome: Boolean): Pair<ImageVector, Color> {
    // Default values
    val defaultIcon = if (isIncome) Icons.Default.Add else Icons.Default.KeyboardArrowDown
    val defaultColor = if (isIncome) AccentGreen else AccentRed
    
    // For a real app, you might want to categorize transactions and provide different icons
    // This is a simple example
    return when {
        title.contains("Mercado", ignoreCase = true) -> Pair(Icons.Default.ShoppingCart, AccentOrange)
        else -> Pair(defaultIcon, defaultColor)
    }
}

@Preview
@Composable
fun TransactionCardPreview() {
    TransactionCard(
        transaction = TransactionModel(
            id = "1",
            title = "Compra de Mercado",
            date = "2024-06-15",
            amount = "R$ -150,00"
        ),
        onClick = {}
    )
}

@Preview
@Composable
fun IncomeTransactionCardPreview() {
    TransactionCard(
        transaction = TransactionModel(
            id = "2",
            title = "Salário",
            date = "2024-06-01",
            amount = "R$ 3.500,00"
        ),
        onClick = {}
    )
}