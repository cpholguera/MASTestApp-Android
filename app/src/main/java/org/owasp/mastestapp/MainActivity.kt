package org.owasp.mastestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold

import androidx.compose.ui.text.font.FontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MyScreenContent(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun GradientButton(onClick: () -> Unit) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF02D6A9), // rgb(2, 214, 169)
            Color(0xFF479FFF)  // rgb(71, 159, 255)
        )
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .background(gradientBrush, shape = RoundedCornerShape(30.dp))
            .padding(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Text(text = "Start", fontFamily = FontFamily.SansSerif, fontSize = 14.sp,)
    }
}

@Preview
@Composable
fun MyScreenContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val mastgTestClass = MastgTest(context)
    var displayString by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp, 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Display the fixed text
            Text(
                text = "OWASP MAS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            // Button to update the display string
            GradientButton(
                onClick = {
                    displayString = mastgTestClass.mastgTest()
                }
            )
        }

        // Text area with rounded corners and console-style font
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .background(Color.DarkGray, RoundedCornerShape(8.dp))
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = displayString,
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}