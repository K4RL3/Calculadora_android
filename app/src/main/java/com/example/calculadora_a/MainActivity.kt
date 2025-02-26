package com.example.calculadora_a

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadora_a.ui.theme.Calculadora_aTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculadora_aTheme {
                Calculada()
                }
            }
        }
    }

@Composable
fun Calculada() {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        Text(text = "Numeritos", modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.Blue)
            .height(50.dp),
            textAlign = TextAlign.Right,
        )

        Column (modifier = Modifier.fillMaxSize()
            .background(Color.DarkGray)){
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()) {
                Boton("7")
                Boton("8")
                Boton("9")

            }
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()) {
                Boton("4")
                Boton("5")
                Boton("6")

            }
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()) {
                Boton("1")
                Boton("2")
                Boton("3")

            }
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()) {
                Boton(".")
                Boton("0")
                Boton("op")

            }

        }
    }

}


@Composable
fun Boton(etiqueta: String){
    Text(
        etiqueta, modifier = Modifier
            .padding(25.dp)
            .background(Color.Green)
            .height(50.dp)
            .width(50.dp),
        textAlign = TextAlign.Center,
        //fontSize
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculadora_aTheme {
        Calculada()
    }
}

@Preview
@Composable
fun  mostrar_btn() {
    Calculadora_aTheme {
        Boton("4")
    }
    
}