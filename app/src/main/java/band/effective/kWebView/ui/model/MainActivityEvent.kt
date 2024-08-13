package band.effective.kWebView.ui.model

sealed interface MainActivityEvent {
    data object ToBack: MainActivityEvent
    data object ToXml: MainActivityEvent
    data object ToCompose: MainActivityEvent
}