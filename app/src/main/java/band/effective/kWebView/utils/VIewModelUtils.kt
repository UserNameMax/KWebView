package band.effective.kWebView.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

context(ViewModel)
fun<T> MutableSharedFlow<T>.push(value: T) = viewModelScope.launch { emit(value) }