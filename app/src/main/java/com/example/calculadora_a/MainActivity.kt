package com.example.calculadora_a

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora_a.ui.theme.Blanc
import com.example.calculadora_a.ui.theme.Calculadora_aTheme
import com.example.calculadora_a.ui.theme.GreenB
import com.example.calculadora_a.ui.theme.GreenN
import com.example.calculadora_a.ui.theme.PantaGr
import com.example.calculadora_a.ui.theme.Pink40

data class BotonModelo(
    val id: String,
    var numero: String,
    var operacion_aritmetica: OperacionesAritmeticas = OperacionesAritmeticas.Ninguna,
    var operacion_a_mostrar: String = ""
) {}

enum class EstadosCalculadora{
    CuandoEstaEnCero,
    AgregandoNumeros,
    SeleccionadoOperacion,
    MostrandoResultado
}

enum class OperacionesAritmeticas{
    Ninguna,
    Suma,
    Resta,
    Multiplicacion,
    Division,
    Resultado
}

var hileras_de_botones_a_dibujar = arrayOf(
    arrayOf(
        BotonModelo("btn_9", "9", OperacionesAritmeticas.Multiplicacion, "*"),
        BotonModelo("btn_8", "8"),
        BotonModelo("btn_7", "7", OperacionesAritmeticas.Division, "/"),
    ),
    arrayOf(
        BotonModelo("btn_6", "6"),
        BotonModelo("btn_5", "5", OperacionesAritmeticas.Resultado, "="),
        BotonModelo("btn_4", "4"),
    ),
    arrayOf(
        BotonModelo("btn_3", "3", OperacionesAritmeticas.Suma, "+"),
        BotonModelo("btn_2", "2"),
        BotonModelo("btn_1", "1", OperacionesAritmeticas.Resta, "-"),
    ),
    arrayOf(
        BotonModelo("btn_p", "."),
        BotonModelo("btn_0", "0"),
        BotonModelo("btn_op", "OP"),
    )

)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculadora_aTheme {
                Calculadora()
            }
        }
    }
}

@Composable
fun Calculadora() {
    var pantalla_cal = remember { mutableStateOf("0") }
    var numero_anterior = remember { mutableStateOf("0") }
    var estado_de_la_calculadora = remember { mutableStateOf(EstadosCalculadora.CuandoEstaEnCero) }
    var operacion_seleccionada = remember { mutableStateOf(OperacionesAritmeticas.Ninguna) }

    fun pulsar_boton(boton: BotonModelo){
        Log.v("BTN_INTERFAZ", "Se ha pulsado el boton ${boton.id}")
        Log.v("OP_SELECCIONADA", "La operacion es ${operacion_seleccionada.value}")

        when(estado_de_la_calculadora.value){
            EstadosCalculadora.CuandoEstaEnCero -> {
                if(boton.id == "boton_0"){
                    return
                }
                else if(boton.id == "boton_punto"){
                    pantalla_cal.value = pantalla_cal.value + boton.numero
                    return
                }

                pantalla_cal.value = boton.numero
                estado_de_la_calculadora.value = EstadosCalculadora.AgregandoNumeros

            }

            EstadosCalculadora.AgregandoNumeros -> {
                if(boton.id == "btn_op"){
                    estado_de_la_calculadora.value = EstadosCalculadora.SeleccionadoOperacion
                    return
                }

                pantalla_cal.value = pantalla_cal.value + boton.numero
            }

            EstadosCalculadora.SeleccionadoOperacion -> {
                if(     boton.operacion_aritmetica != OperacionesAritmeticas.Ninguna &&
                    boton.operacion_aritmetica != OperacionesAritmeticas.Resultado
                ){
                    operacion_seleccionada.value = boton.operacion_aritmetica
                    estado_de_la_calculadora.value = EstadosCalculadora.CuandoEstaEnCero

                    numero_anterior.value = pantalla_cal.value

                    pantalla_cal.value = "0"
                    return
                }
                // Aqui imprimimos el resultado
                else if(boton.operacion_aritmetica == OperacionesAritmeticas.Resultado &&
                    operacion_seleccionada.value != OperacionesAritmeticas.Ninguna){

                    when(operacion_seleccionada.value){

                        OperacionesAritmeticas.Suma -> {
                            pantalla_cal.value = numero_anterior.value + "+" + pantalla_cal.value
                        }
                        OperacionesAritmeticas.Resta -> {
                            pantalla_cal.value = numero_anterior.value + "-" + pantalla_cal.value
                        }
                        OperacionesAritmeticas.Multiplicacion -> {
                            pantalla_cal.value = numero_anterior.value + "*" + pantalla_cal.value
                        }
                        OperacionesAritmeticas.Division -> {
                            pantalla_cal.value = numero_anterior.value + "/" + pantalla_cal.value
                        }

                        else -> {}
                    }
                    estado_de_la_calculadora.value = EstadosCalculadora.MostrandoResultado
                    return
                }
                estado_de_la_calculadora.value = EstadosCalculadora.AgregandoNumeros
            }
            EstadosCalculadora.MostrandoResultado -> {
                numero_anterior.value  = ""
                pantalla_cal.value = "0"
                estado_de_la_calculadora.value = EstadosCalculadora.CuandoEstaEnCero
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(color = GreenB),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "${pantalla_cal.value}", modifier = Modifier
            .padding(25.dp)
            .fillMaxWidth()
            //.fillMaxHeight(0.33f)
            .background(color = PantaGr)
            .height(200.dp),
            textAlign = TextAlign.End,
            color = Color.White,
            fontSize = 55.sp,
            maxLines = 5,
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = GreenB),
            verticalArrangement = Arrangement.SpaceEvenly) {
            for(fila_de_botones in hileras_de_botones_a_dibujar){
                Row(horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)) {
                    for(boton_a_dibujar in fila_de_botones){
                        when(estado_de_la_calculadora.value){
                            EstadosCalculadora.SeleccionadoOperacion -> {
                                Boton(boton_a_dibujar.operacion_a_mostrar, alPulsar = {
                                    pulsar_boton(boton_a_dibujar)
                                })
                            }
                            else -> {
                                Boton(boton_a_dibujar.numero, alPulsar = {
                                    pulsar_boton(boton_a_dibujar)
                                })
                            }
                        }

                    }
                }
            }
        }
    }
}
@Composable
fun Boton(etiqueta: String, alPulsar: () -> Unit = {}){
    Button(
        onClick = alPulsar,
        modifier = Modifier
            .size(80.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = GreenN),

        ){
        Text(etiqueta,
            modifier = Modifier,
            textAlign = TextAlign.Center,
            color = Blanc,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculadora_aTheme {
        Calculadora()
    }
}

@Preview
@Composable
fun  mostrar_btn() {
    Calculadora_aTheme {
        Boton("4")
    }

}