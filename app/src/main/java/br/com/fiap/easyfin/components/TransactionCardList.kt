package br.com.fiap.easyfin.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.fiap.easyfin.model.TransactionModel

@Composable
fun TransactionCardList(
    transactions: List<TransactionModel>,
    onTransactionClick: (TransactionModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(transactions) { transaction ->
            TransactionCard(
                transaction = transaction,
                onClick = { onTransactionClick(transaction) }
            )
        }
    }
}