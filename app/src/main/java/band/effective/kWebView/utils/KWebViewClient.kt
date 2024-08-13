package band.effective.kWebView.utils

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient

class KWebViewClient(
    private val onReceiveError: (view: WebView?, request: WebResourceRequest?, error: WebResourceError?) -> Unit,
    private val onReceiveHttpError: (view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) -> Unit,
    private val shouldOverrideUrlLoad: (view: WebView?, request: WebResourceRequest?) -> Boolean,
    private val onPageStart: (view: WebView?, url: String?, favicon: Bitmap?) -> Unit,
    private val onPageFinish: (view: WebView?, url: String?) -> Unit,
    private val onUpdateVisitedHistory: (view: WebView?, url: String?, isReload: Boolean) -> Unit
) : WebViewClient() {
    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?
    ) {
        onReceiveHttpError(view, request, errorResponse)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        onReceiveError(view, request, error)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return shouldOverrideUrlLoad(view, request)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        onPageStart(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        onPageFinish(view, url)
    }

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        onUpdateVisitedHistory(view, url, isReload)
    }
}

fun webViewClient(
    onReceiveError: (view: WebView?, request: WebResourceRequest?, error: WebResourceError?) -> Unit = { _, _, _ -> },
    onReceiveHttpError: (view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) -> Unit = { _, _, _ -> },
    shouldOverrideUrlLoad: (view: WebView?, request: WebResourceRequest?) -> Boolean = { _, _ -> false },
    onPageStart: (view: WebView?, url: String?, favicon: Bitmap?) -> Unit = { _, _, _ -> },
    onPageFinish: (view: WebView?, url: String?) -> Unit = { _, _ -> },
    onUpdateVisitedHistory: (view: WebView?, url: String?, isReload: Boolean) -> Unit = { _, _, _ -> }
) = KWebViewClient(
    onReceiveError = onReceiveError,
    onReceiveHttpError = onReceiveHttpError,
    shouldOverrideUrlLoad = shouldOverrideUrlLoad,
    onPageStart = onPageStart,
    onPageFinish = onPageFinish,
    onUpdateVisitedHistory = onUpdateVisitedHistory
)