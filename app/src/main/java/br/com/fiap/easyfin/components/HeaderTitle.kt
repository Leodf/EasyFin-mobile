package br.com.fiap.easyfin.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun HeaderTitle(name: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = "Bem-vindo de volta!",
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}