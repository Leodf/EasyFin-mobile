package br.com.fiap.easyfin.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun BottomNavigationBar() {
    val bottomMenuItemsList = prepareBottomMenu()
    var selectedItem by remember {
        mutableStateOf("Inicio")
    }

    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            bottomMenuItemsList.forEachIndexed { index, bottomMenuItem ->
                NavigationBarItem(
                    selected = (selectedItem == bottomMenuItem.label),
                    onClick = {
                        selectedItem = bottomMenuItem.label
                    },
                    icon = {
                        Icon(
                            imageVector = bottomMenuItem.icon,
                            contentDescription = bottomMenuItem.label,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            text = bottomMenuItem.label,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }
    }
}

data class BottomMenuItem(
    val label: String,
    val icon: ImageVector
)

@Composable
fun prepareBottomMenu():List<BottomMenuItem>{
    return listOf(
        BottomMenuItem(
            label = "Inicio",
            icon = Icons.Rounded.Home
        ),
        BottomMenuItem(
            label = "Movimentações",
            icon = Icons.Rounded.Refresh
        ),
        BottomMenuItem(
            label = "Perfil",
            icon = Icons.Rounded.AccountCircle
        ),
    )
}