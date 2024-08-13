package band.effective.kWebView.ui.utils

import android.widget.Button
import band.effective.kWebView.ui.model.MainActivityEvent
import band.effective.kWebView.ui.MainViewModel

context(MainViewModel)
fun Button.setSendingEvent(event: MainActivityEvent) {
    val viewModel: MainViewModel = this@MainViewModel
    setOnClickListener { viewModel.sendEvent(event) }
}