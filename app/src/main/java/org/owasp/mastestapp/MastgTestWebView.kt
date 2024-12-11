package org.owasp.mastestapp

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient

class MastgTestWebView (private val context: Context){

    fun mastgTest(webView: WebView) {
        webView.apply {
            webViewClient = object : WebViewClient() {}
            loadUrl("https://mas.owasp.org/")
        }
    }

}
