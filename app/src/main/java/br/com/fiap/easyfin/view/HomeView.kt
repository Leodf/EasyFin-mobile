package br.com.fiap.easyfin.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.com.fiap.easyfin.components.BalanceCard
import br.com.fiap.easyfin.components.BottomNavigationBar
import br.com.fiap.easyfin.components.EmptyState
import br.com.fiap.easyfin.components.HeaderTitle
import br.com.fiap.easyfin.components.SearchBar
import br.com.fiap.easyfin.components.SectionTitle
import br.com.fiap.easyfin.components.SummaryCard
import br.com.fiap.easyfin.components.TransactionCard
import br.com.fiap.easyfin.model.TransactionModel
import br.com.fiap.easyfin.viewmodel.HomeViewModel

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel = viewModel()
) {
    val userName by homeViewModel.userName
    val scrollState = rememberScrollState()
    
    // Estado para a funcionalidade de busca
    var searchQuery by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    
    // Transações de exemplo - em um app real, viriam de um ViewModel
    val allTransactions = listOf(
        TransactionModel(
            id = "1",
            title = "Compra de Mercado",
            date = "2024-06-20",
            amount = "R$ -100,00"
        ),
        TransactionModel(
            id = "2",
            title = "Aluguel",
            date = "2024-06-01",
            amount = "R$ -2.000,00"
        ),
        TransactionModel(
            id = "3",
            title = "Salário",
            date = "2024-06-15",
            amount = "R$ 3.500,00"
        ),
        TransactionModel(
            id = "4",
            title = "Academia",
            date = "2024-06-10",
            amount = "R$ -150,00"
        )
    )
    
    // Filtra transações baseado na busca
    val filteredTransactions = if (searchQuery.isEmpty()) {
        allTransactions
    } else {
        allTransactions.filter { 
            it.title.contains(searchQuery, ignoreCase = true) ||
            it.amount.contains(searchQuery, ignoreCase = true) ||
            it.date.contains(searchQuery, ignoreCase = true)
        }
    }
    
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onItemSelected = { selectedItem ->
                    when(selectedItem) {
                        "Inicio" -> { /* Já está na home, não faz nada */ }
                        "Movimentações" -> navController.navigate("transactions")
                        "Perfil" -> navController.navigate("profile")
                    }
                },
                onAddButtonClick = {
                    navController.navigate("add_transaction")
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 16.dp,
                        bottom = padding.calculateBottomPadding()
                    )
            ) {
                // Seção do Cabeçalho
                HeaderTitle(
                    name = userName,
                    onLogout = {
                        homeViewModel.logout()
                        navController.navigate("auth") {
                            popUpTo("home") {
                                inclusive = true
                            }
                        }
                    }
                )
                
                // Barra de Busca
                SearchBar(
                    value = searchQuery,
                    onValueChange = { 
                        searchQuery = it 
                        isSearching = it.isNotEmpty()
                    },
                    onSearch = { /* Executa busca */ },
                    onFilterClick = { /* Mostra opções de filtro */ }
                )
                
                if (!isSearching) {
                    // Conteúdo principal quando não está buscando
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .verticalScroll(scrollState)
                    ) {
                        // Seção do Saldo
                        BalanceCard(
                            balance = 2500.0,
                            onOptionsClick = { /* Mostra opções de saldo */ }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Seção do Resumo Financeiro
                        SectionTitle("Resumo Financeiro")
                        SummaryCard()
                        
                        // Seção de Transações
                        SectionTitle("Movimentações Recentes")
                        
                        if (allTransactions.isEmpty()) {
                            // Mostra estado vazio quando não há transações
                            EmptyState(
                                title = "Nenhuma transação",
                                message = "Você ainda não possui nenhuma transação. Adicione sua primeira transação clicando no botão abaixo.",
                                icon = Icons.Default.List,
                                buttonText = "Adicionar Transação",
                                onButtonClick = { navController.navigate("add_transaction") }
                            )
                        } else {
                            // Mostra as transações
                            allTransactions.forEach { transaction ->
                                TransactionCard(
                                    transaction = transaction,
                                    onClick = {
                                        // Navega para detalhes da transação
                                        navController.navigate("transaction_details/${transaction.id}")
                                    }
                                )
                            }
                        }
                        
                        // Adiciona espaço no final para melhor experiência do usuário
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                } else {
                    // Resultados da busca
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        if (filteredTransactions.isEmpty()) {
                            // Mostra estado vazio para nenhum resultado encontrado
                            EmptyState(
                                title = "Nenhum resultado",
                                message = "Não encontramos nenhuma transação para \"$searchQuery\". Tente usar termos diferentes.",
                                icon = Icons.Default.List
                            )
                        } else {
                            // Mostra transações filtradas
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                item {
                                    SectionTitle("Resultados da Busca")
                                }
                                
                                items(filteredTransactions) { transaction ->
                                    TransactionCard(
                                        transaction = transaction,
                                        onClick = {
                                            // Navega para detalhes da transação
                                            navController.navigate("transaction_details/${transaction.id}")
                                        }
                                    )
                                }
                                
                                item {
                                    Spacer(modifier = Modifier.height(80.dp))
                                }
                            }
                        }
                    }
                }
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