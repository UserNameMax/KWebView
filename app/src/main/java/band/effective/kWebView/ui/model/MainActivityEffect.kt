package band.effective.kWebView.ui.model

sealed interface MainActivityEffect {
    data object ToBack: MainActivityEffect
    data object ToXml: MainActivityEffect
    data object ToCompose: MainActivityEffect
}