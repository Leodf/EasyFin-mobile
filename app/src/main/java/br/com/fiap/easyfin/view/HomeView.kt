package br.com.fiap.easyfin.view

import SummaryCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.easyfin.components.HeaderTitle
import br.com.fiap.easyfin.components.SectionTitle
import br.com.fiap.easyfin.components.TransactionCard
import br.com.fiap.easyfin.model.TransactionModel
import br.com.fiap.easyfin.viewmodel.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController


@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel = viewModel()
) {

    val userName by homeViewModel.userName

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Evento")
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                HeaderTitle(userName, onLogout = {
                    homeViewModel.logout()
                    navController.navigate("auth") {
                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                })
                Spacer(modifier = Modifier.height(8.dp))
                SectionTitle("Resumo Financeiro")
                SummaryCard()
                SectionTitle("Movimentações")
                Spacer(modifier = Modifier.height(8.dp))

                val transactions = listOf(
                    TransactionModel(
                        id = "1",
                        title = "Compra de Mercado",
                        date = "2024-06-20",
                        amount = "R$ 100,00"
                    ),
                    TransactionModel(
                        id = "2",
                        title = "Aluguel",
                        date = "2024-06-01",
                        amount = "R$ 2.000,00"
                    ),
                    TransactionModel(
                        id = "3",
                        title = "Academia",
                        date = "2024-06-15",
                        amount = "R$ 150,00"
                    )
                )

                TransactionCard(transactions[0], onClick = {})
                TransactionCard(transactions[1], onClick = {})
                TransactionCard(transactions[2], onClick = {})



            }
        }
    )
}

/* @Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun HomeViewPreview(){
    HomeView()
}
/
 */