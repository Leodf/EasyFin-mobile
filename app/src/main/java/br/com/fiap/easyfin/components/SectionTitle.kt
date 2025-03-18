package br.com.fiap.easyfin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SectionTitle(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Accent indicator
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(24.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            // Title text
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SectionTitlePreview() {
    SectionTitle("Resumo Financeiro")
}