package com.example.jetpackcomposetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    MyMessage("Yo se que fue una noche nama", "Lorem ipsum dolor sit amet consectetur adipiscing elit tellus curae parturient urna, ullamcorper curabitur porta bibendum consequat malesuada accumsan ante nostra. Senectus integer taciti nulla sapien nisi porttitor feugiat arcu justo nostra, turpis penatibus elementum erat magnis odio aptent risus nibh faucibus, habitant congue nunc tempus massa luctus a semper leo."),
    MyMessage("tal vez en ti quise encontrar", "lo que en otro perdi"),
    MyMessage("Tu orgullo no me quiere hablar","Lorem ipsum dolor sit amet consectetur adipiscing elit tellus curae parturient urna, ullamcorper curabitur porta bibendum consequat malesuada accumsan ante nostra. Senectus integer taciti nulla sapien nisi porttitor feugiat arcu justo nostra, turpis penatibus elementum erat magnis odio aptent risus nibh faucibus, habitant congue nunc tempus massa luctus a semper leo."),
    MyMessage("No me gusta perder", "Lorem ipsum dolor sit amet consectetur adipiscing elit tellus curae parturient urna ullamcorper" ),
    MyMessage("Me paso mirando el cel", "Aunque me tarde juro que te voy a responder")

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
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier
        .padding(start = 16.dp)
        .clickable {
            expanded = !expanded
        }) {
        MyText(text = message.title, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(16.dp))
        MyText(
            text = message.body,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.labelSmall,
                lines = if (expanded) Int.MAX_VALUE else 1)
    }
}

@Composable
fun MyText(text: String, color: Color, style: TextStyle, lines: Int = Int.MAX_VALUE) {
    Text(text = text, color = color, style = style, maxLines = lines)
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
