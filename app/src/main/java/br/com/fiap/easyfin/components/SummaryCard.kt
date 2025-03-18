import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SummaryCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TotalBalanceCard(balance = 2000.0)
        Spacer(modifier = Modifier.height(16.dp))

        FinancialInfoCard(
            title = "Receitas",
            amount = "R$ 1.000,00",
            color = Color(0xFF81C784) // Verde
        )
        
        FinancialInfoCard(
            title = "Gastos",
            amount = "R$ 500,00",
            color = Color(0xFFE57373) // Vermelho
        )

        FinancialInfoCard(
            title = "Investimentos",
            amount = "R$ 1.500,00",
            color = Color(0xFFFFD54F) // Amarelo
        )
    }
}

@Composable
fun FinancialInfoCard(title: String, amount: String, color: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.15f))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.weight(1f),
                color = color
            )

            Text(
                text = amount,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = color
            )
        }
    }
}

@Composable
fun TotalBalanceCard(balance: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)) // Azul claro
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Saldo Total",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color(0xFF1976D2) // Azul escuro
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "R$ %.2f".format(balance),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF1E88E5) // Azul escuro forte
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEnhancedSummaryCard() {
    SummaryCard()
}
