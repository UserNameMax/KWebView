package band.effective.kWebView.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import band.effective.kWebView.ui.model.MainActivityEvent
import band.effective.kWebView.ui.MainViewModel
import band.effective.kWebView.databinding.FragmentMenuBinding
import band.effective.kWebView.ui.utils.setSendingEvent

class MenuFragment : Fragment() {

    private var nullableBinding: FragmentMenuBinding? = null
    private val binding get() = nullableBinding!!

    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        with(mainViewModel) {
            binding.xmlButton.setSendingEvent(MainActivityEvent.ToXml)
            binding.composeButton.setSendingEvent(MainActivityEvent.ToCompose)
        }
        super.onStart()
    }
}