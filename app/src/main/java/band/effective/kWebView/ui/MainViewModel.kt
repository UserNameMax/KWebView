package band.effective.kWebView.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import band.effective.kWebView.ui.model.MainActivityEffect
import band.effective.kWebView.ui.model.MainActivityEvent
import band.effective.kWebView.utils.push
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val mutableEffect = MutableSharedFlow<MainActivityEffect>()
    val effect = mutableEffect.asSharedFlow()

    fun sendEvent(event: MainActivityEvent) {
        when (event) {
            MainActivityEvent.ToBack -> mutableEffect.push(MainActivityEffect.ToBack)
            MainActivityEvent.ToCompose -> mutableEffect.push(MainActivityEffect.ToCompose)
            MainActivityEvent.ToXml -> mutableEffect.push(MainActivityEffect.ToXml)
        }
    }
}