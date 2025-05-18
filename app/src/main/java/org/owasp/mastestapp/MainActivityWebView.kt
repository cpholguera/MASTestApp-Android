package org.owasp.mastestapp

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

class MainActivityWebView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WebViewScreen()
        }
    }
}

@Composable
@Preview
fun WebViewScreen() {
    val context = LocalContext.current
    val mastgTestWebViewClass = MastgTestWebView(context)
    var showWebView by remember { mutableStateOf(false) } // Controls whether the WebView is displayed
    var webViewKey by remember { mutableIntStateOf(0) }   // Unique key to force WebView recreation

    BaseScreen(
        onStartClick = {
            showWebView = true // Set to show the WebView
            webViewKey++       // Increment key to create/recreate WebView
        }
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
                modifier = Modifier.padding(16.dp),
                text = "Click \"Start\" to open the WebView.",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}