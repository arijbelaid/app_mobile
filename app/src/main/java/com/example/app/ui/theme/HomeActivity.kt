package com.example.app.ui.theme

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.Greeting
import com.example.app.MainActivity
import com.example.app.R
import com.example.app.ui.theme.ui.theme.AppTheme


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting4(
                        name = "bienvenue",
                        modifier = Modifier.padding(innerPadding)
                    )
                    GreetingScreen()
                }
            }
        }
    }
}
@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting4(
            name = "User")
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.welcom),
            contentDescription = "Welcome Image",
            modifier = Modifier
                .size(250.dp)
                .padding(bottom = 30.dp)
        )
        Text(
            text = "Please Login",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(35.dp))

        Button (onClick = {
            // Déconnexion
            with(sharedPreferences.edit()) {
                putBoolean("isLoggedIn", false)
                apply()
            }

            // Retourner à l'écran de connexion
            val intent =  Intent(context,MainActivity::class.java)
            context.startActivity(intent)
            (context as ComponentActivity).finish() // Fermer MainActivity5
        }) {
            Text(text = "Logout")
        }
    }
}


@Composable
fun Greeting4(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Welcome, $name!",
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    AppTheme {
        GreetingScreen()

    }
}