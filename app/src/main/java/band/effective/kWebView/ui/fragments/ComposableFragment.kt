package band.effective.kWebView.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import band.effective.kWebView.R
import band.effective.kWebView.databinding.FragmentComposableBinding
import band.effective.kWebView.ui.theme.KWebViewTheme
import band.effective.kWebView.utils.WebView
import band.effective.kWebView.utils.chromeClient
import band.effective.kWebView.utils.webViewClient

class ComposableFragment : Fragment() {

    private var nullableBinding: FragmentComposableBinding? = null
    private val binding get() = nullableBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = FragmentComposableBinding.inflate(inflater, container, false)
        binding.composeView.setContent { Content() }
        return binding.root
    }

    @Composable
    private fun Content() {
        KWebViewTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                WebView(
                    modifier = Modifier.padding(innerPadding).fillMaxSize(),
                    chromeClient = chromeClient {
                        Log.i("chrome", it?.message().toString())
                    },
                    webViewClient = webViewClient(onPageFinish = { _, url ->
                        Log.i("onPageFinish", url.toString())
                    })
                ) { webView ->
                    webView.settings.javaScriptEnabled = true
                    webView.loadUrl("https://www.google.com/")
                }
            }
        }
    }
}