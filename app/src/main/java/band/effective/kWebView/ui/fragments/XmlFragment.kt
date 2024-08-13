package band.effective.kWebView.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import band.effective.kWebView.databinding.FragmentXmlBinding
import band.effective.kWebView.utils.chromeClient
import band.effective.kWebView.utils.setup
import band.effective.kWebView.utils.webViewClient

class XmlFragment : Fragment() {

    private var nullableBinding: FragmentXmlBinding? = null
    private val binding get() = nullableBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = FragmentXmlBinding.inflate(inflater, container, false)
        binding.xmlWebView.init()
        return binding.root
    }

    private fun WebView.init() {
        setup(
            chromeClient = chromeClient { Log.i("chrome", it?.message().toString()) },
            webViewClient = webViewClient(onPageFinish = { _, url ->
                Log.i("onPageFinish", url.toString())
            })
        )
        settings.javaScriptEnabled = true
        loadUrl("https://www.google.com/")
    }
}