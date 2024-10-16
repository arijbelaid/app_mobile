package com.example.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.HomeActivity
import kotlin.reflect.KProperty


private val Typography.h5: TextStyle
    get() {
        TODO("Not yet implemented")
    }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "bienvenue",
                        modifier = Modifier.padding(innerPadding)
                    )
                    photoLogin()
                    PageConnecter()
                }
            }
        }
    }
}

@Composable
fun PageConnecter() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }


    var contexte = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() && it.isNotEmpty()
                },
                label = { Text("Email") },
                isError = emailError,
                modifier = Modifier.fillMaxWidth())

            if (emailError) {
                Text("Invalid email format", color = Color.Red, style = MaterialTheme.typography.bodySmall)
            }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            var intent = Intent(contexte, HomeActivity::class.java)
            intent.putExtra("","")
            contexte.startActivity(intent)



        }) {
            Text("Se connecter")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {

            var intent = Intent(contexte, signUP::class.java)
            intent.putExtra("","")
            contexte.startActivity(intent)



            if (email.isEmpty() || password.isEmpty()) {
                errorMessage = "Tous les champs doivent être remplis"
            } else {
                errorMessage = "succée"
                // Logique de connexion ici
            }
        }) {
            Text("creer un compte")
        }
        Spacer(modifier = Modifier.height(8.dp))

            Text(
            text = "mot de passe oublié",
            modifier = Modifier.clickable{
            var intent = Intent(contexte, verifierMdp::class.java)
            intent.putExtra("","")
            contexte.startActivity(intent)


        })
        }
        Spacer(modifier = Modifier.height(8.dp)
        )
    }
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = " $name!",
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        color = Color.Blue,
        fontWeight = FontWeight.Bold,
    )
}
@Composable
fun photoLogin(modifier: Modifier = Modifier) {
    // Suppression de l'extension .png dans l'accès à la ressource
    val res = painterResource(id = R.drawable.connecter)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = res,
            contentDescription = "Image de connexion",  // Meilleure description de l'image
            modifier = Modifier.fillMaxSize(),  // Utilisation de Modifier plutôt que de modifier
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("bienvenue")
    }
}