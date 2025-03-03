package com.example.calculadora_a

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora_a.ui.theme.Calculadora_aTheme

data class BotonModelo(val id: String, var numero: String){}

var hileras_de_btn_a_dibujar = arrayOf(
    arrayOf(
        BotonModelo("btn_9","9",),
        BotonModelo("btn_8","8",),
        BotonModelo("btn_7","7",)
    ),
    arrayOf(
        BotonModelo("btn_6","6",),
        BotonModelo("btn_5","5",),
        BotonModelo("btn_4","4",)
    ),
    arrayOf(
        BotonModelo("btn_3","3",),
        BotonModelo("btn_2","2",),
        BotonModelo("btn_1","1",)
    ),
    arrayOf(
        BotonModelo("btn_punto",".",),
        BotonModelo("btn_0","0",),
        BotonModelo("btn_op","OP",)
    )
)


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
    var  pantalla_cal = remember { mutableStateOf("0") }
    var  estados_de_cal = remember { mutableStateOf("modtrar_numeros") }

    fun pulsar_btn(boton: BotonModelo){
        Log.v("BTN_INTERFAZ", "Se ha pulsado el boton ${boton.id} de la interfaz")
        if (boton.id == "btn_op"){
            estados_de_cal.value = "mostrar_ops"
        }

        if (estados_de_cal.value == "mostrar_nums"){
            pantalla_cal.value = boton.numero
        }
        else{
            pantalla_cal.value = boton.id
        }

    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        Text(text = "${pantalla_cal.value}", modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.Blue)
                    .height(50.dp),
            textAlign = TextAlign.Right,
            color = Color.White,
            fontSize = 56.sp
        )

        Column (modifier = Modifier.fillMaxSize().background(Color.DarkGray)){
            for (fila_de_btn in hileras_de_btn_a_dibujar){
                Row(horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()) {
                    for (btn_a_dibujar in fila_de_btn){
                        Boton(btn_a_dibujar.numero, alPulsar ={
                            pulsar_btn(btn_a_dibujar)
                        })
                }
                }
            }

        }

    }
}




@Composable
fun Boton(etiqueta: String, alPulsar: () -> Unit = {}){
    Button(onClick = alPulsar){
        Image(
            painter = painterResource(R.drawable.conde),
            contentDescription = "una foto de perfil del conde de contar",
            modifier = Modifier.size(25.dp)
        )
    }

    Text(
        etiqueta, modifier = Modifier
            .background(Color.Green),
        textAlign = TextAlign.Center,
        color = Color.Red
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