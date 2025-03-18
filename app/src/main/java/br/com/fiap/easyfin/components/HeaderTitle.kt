package br.com.fiap.easyfin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun HeaderTitle(name: String, onLogout: () -> Unit) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column {
            Text(
                text = "Bem-vindo de volta!",
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium,
            )

        }
        Icon(
            imageVector = Icons.Rounded.ExitToApp,
            contentDescription = "Person Icon",
            modifier = Modifier
                .size(32.dp)
                .clickable { onLogout() }
            ,
            tint = MaterialTheme.colorScheme.primary
        )
    }

}

@Preview(showBackground = true)
@Composable
fun HeaderTitlePreview() {
    HeaderTitle("John Doe", onLogout = {})
}