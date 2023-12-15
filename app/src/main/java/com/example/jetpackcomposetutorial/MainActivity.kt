package com.example.jetpackcomposetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

val messages: List<MyMessage> = listOf(
    MyMessage("Esto es una prueba","es un intento"),
    MyMessage("Otro contenido de la prueba"," efectivamente"),
    MyMessage("Beibi dime la verdad","si te olvidaste de mi"),
    MyMessage("Yo se que fue una noche nama", "que no se vuelve a repetir"),
    MyMessage("tal vez en ti quise encontrar", "lo que en otro perdi"),
    MyMessage("Tu orgullo no me quiere hablar","entonces vamo a repetir")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorialTheme {
                MyMessageList(messages = messages)
            }
        }
    }
}

data class MyMessage(val title: String, val body: String)

@Composable
fun MyMessageList(messages: List<MyMessage>) {
    LazyColumn {
        items(messages) { message ->
            MyMessageItem(message = message)
        }
    }
}

@Composable
fun MyMessageItem(message: MyMessage) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        MyImage()
        MyTexts(message = message)
    }
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "imagenPrueba",
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .size(64.dp)
    )
}

@Composable
fun MyTexts(message: MyMessage) {
    Column(modifier = Modifier.padding(start = 16.dp)) {
        MyText(text = message.title, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(16.dp))
        MyText(text = message.body, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun MyText(text: String, color: Color, style: TextStyle) {
    Text(text = text, color = color, style = style)
}

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewComponent() {
    JetpackComposeTutorialTheme {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            MyMessageList(messages)
        }
    }
}
