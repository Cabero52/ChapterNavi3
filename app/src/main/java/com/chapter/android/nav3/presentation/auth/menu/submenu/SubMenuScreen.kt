package com.chapter.android.nav3.presentation.auth.menu.submenu


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubMenuScreen(
    onList: () -> Unit = {},
    onCart: () -> Unit = {}
) {
    Scaffold(topBar = { TopAppBar(title = { Text("SubMenu") }) }) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = onList, modifier = Modifier.fillMaxWidth()) {
                Text("List")
            }
            Button(onClick = onCart, modifier = Modifier.fillMaxWidth()) {
                Text("Cart")
            }
        }
    }
}