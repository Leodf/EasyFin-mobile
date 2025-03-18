package br.com.fiap.easyfin.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.easyfin.model.TransactionModel

@Composable
fun TransactionCardList(modifier: Modifier = Modifier, transactions: List<TransactionModel>, onClick: () -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            count = transactions.size,
            itemContent = { index ->
                TransactionCard(transaction = transactions[index], onClick = onClick)
            }
        )
    }
}