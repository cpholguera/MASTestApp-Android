package org.owasp.mastestapp

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

class MainActivityWebView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                WebViewScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun WebViewScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val mastgTestWebViewClass = MastgTestWebView(context)
    var showWebView by remember { mutableStateOf(false) } // Controls whether the WebView is displayed
    var webViewKey by remember { mutableIntStateOf(0) }      // Unique key to force WebView recreation

    Box(
        modifier = modifier.fillMaxSize().padding(16.dp, 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Row with the title and button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Title
                Text(
                    text = "OWASP MAS",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )

                // "Start" Button
                GradientButton(
                    onClick = {
                        showWebView = true // Set to show the WebView
                        webViewKey++       // Increment key to create/recreate WebView
                    },
                    buttonText = "Start"
                )
            }

            // WebView Container
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (showWebView) Color.Transparent else Color.LightGray)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                if (showWebView) {
                    // Recompose AndroidView with a new key when showWebView is true
                    key(webViewKey) {
                        AndroidView(
                            factory = {
                                WebView(context).apply {
                                    mastgTestWebViewClass.mastgTest(this)
                                    setBackgroundColor(0)
                                }
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                } else {
                    // Placeholder with text
                    Text(
                        text = "Click \"Start\" to open the WebView",
                        fontSize = 24.sp, // Correct font size
                        color = Color.DarkGray,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }

        }
    }
}

@Composable
fun GradientButton(onClick: () -> Unit, buttonText: String) {
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
        Text(
            text = buttonText,
            fontFamily = FontFamily.SansSerif,
            fontSize = 14.sp
        )
    }
}
