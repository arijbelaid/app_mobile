package com.example.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.AppTheme

class verifierMdp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        name = "verification de mot de passe",
                        modifier = Modifier.padding(innerPadding)
                    )
                    verification()
                }
            }
        }
    }
}

@Composable
fun verification() {
    var email by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }

    var cont = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button (onClick = {
            var intent =Intent(cont ,MainActivity::class.java)
            intent.putExtra("","")
            cont.startActivity(intent)


            if (email.isNotEmpty()) {
                successMessage = "E-mail de réinitialisation envoyé"
                // Logique de réinitialisation ici
            } else {
                successMessage = "Veuillez entrer une adresse e-mail valide"
            }
        }) {
            Text("Réinitialiser le mot de passe")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = successMessage,
            color = if (successMessage.contains("envoyé")) Color.Green else Color.Red
        )
    }
}



@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name!",
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        color = Color.Blue,
        fontWeight = FontWeight.Bold,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    AppTheme {
        Greeting3("verifier mot de passe")
    }
}