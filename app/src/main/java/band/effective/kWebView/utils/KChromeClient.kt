package band.effective.kWebView.utils

import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient

class KChromeClient(private val onConsoleMessageReceive: (consoleMessage: ConsoleMessage?) -> Unit) :
    WebChromeClient() {
    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        onConsoleMessageReceive(consoleMessage)
        return super.onConsoleMessage(consoleMessage)
    }
}

fun chromeClient(onConsoleMessage: (consoleMessage: ConsoleMessage?) -> Unit = {}) =
    KChromeClient(onConsoleMessage)