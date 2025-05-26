package com.melvinbautista.exam.ui

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.melvinbautista.exam.constants.Messages
import com.melvinbautista.exam.constants.States
import com.melvinbautista.exam.constants.UI
import com.melvinbautista.exam.navigations.NavigationScreens
import com.melvinbautista.exam.viewmodel.AuthenticationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
) {
    val activity = LocalContext.current as ComponentActivity
    val viewModel: AuthenticationViewModel = hiltViewModel(activity)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    val authentication by viewModel.authentication.observeAsState()

    LaunchedEffect(authentication?.auth) {
        when (authentication?.auth) {
            States.Registered.SUCCESS -> navController.navigate(NavigationScreens.Login.route)
            States.Registered.FAILED -> Toast.makeText(
                context,
                Messages.Toast.USERNAME_TAKEN,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(UI.TXT_EXAM)
            }
        )
    }) { padding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            TextField(
                label = { Text(UI.TXT_USERNAME) },
                value = username,
                onValueChange = { username = it }, singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            )
            TextField(
                label = { Text(UI.TXT_PASSWORD) },
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),

                visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick = { viewModel.register(username, password) }) {
                Text(UI.TXT_REGISTER)
            }
        }
    }

}
