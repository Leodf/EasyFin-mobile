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
import br.com.fiap.easyfin.components.FinancialInfoCard
import br.com.fiap.easyfin.components.TotalBalanceCard

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FinancialInfoCard(
                title = "Receitas",
                amount = "R$ 1.000,00",
                color = Color(0xFF81C784),
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(5.dp))
            FinancialInfoCard(
                title = "Gastos",
                amount = "R$ 500,00",
                color = Color(0xFFE57373),
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(5.dp))
            FinancialInfoCard(
                title = "Investidos",
                amount = "R$ 1.500,00",
                color = Color(0xFFFFD54F),
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEnhancedSummaryCard() {
    SummaryCard()
}
