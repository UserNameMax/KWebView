package band.effective.kWebView.ui.utils

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class OnBackPress(private val callback: () -> Unit) : OnBackPressedCallback(true) {
    override fun handleOnBackPressed() {
        callback()
    }
}

fun AppCompatActivity.onBackPress(callback: () -> Unit) =
    onBackPressedDispatcher.addCallback(OnBackPress(callback))