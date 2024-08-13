package band.effective.kWebView.utils

import android.content.Context
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

fun WebView.setup(
    chromeClient: WebChromeClient = chromeClient(),
    webViewClient: WebViewClient = webViewClient(),
    nativeApi: Pair<Any, String>? = null
) {
    webChromeClient = chromeClient
    this.webViewClient = webViewClient
    if (nativeApi != null) {
        addJavascriptInterface(nativeApi.first, nativeApi.second)
    }
}

fun WebView(
    context: Context,
    chromeClient: WebChromeClient = chromeClient(),
    webViewClient: WebViewClient = webViewClient(),
    nativeApi: Pair<Any, String>? = null
) = WebView(context).apply {
    setup(
        chromeClient = chromeClient,
        webViewClient = webViewClient,
        nativeApi = nativeApi
    )
}

@Composable
fun WebView(
    modifier: Modifier = Modifier,
    chromeClient: WebChromeClient = chromeClient(),
    webViewClient: WebViewClient = webViewClient(),
    nativeApi: Pair<Any, String>? = null,
    onWebViewCreated: (WebView) -> Unit = {}
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(
                context = context,
                chromeClient = chromeClient,
                webViewClient = webViewClient,
                nativeApi = nativeApi
            ).apply { onWebViewCreated(this) }
        }
    )
}