package band.effective.kWebView.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import band.effective.kWebView.ui.model.MainActivityEffect
import band.effective.kWebView.ui.model.MainActivityEvent
import band.effective.kWebView.databinding.ActivityMainBinding
import band.effective.kWebView.ui.fragments.ComposableFragment
import band.effective.kWebView.ui.fragments.MenuFragment
import band.effective.kWebView.ui.fragments.XmlFragment
import band.effective.kWebView.ui.utils.onBackPress
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private var nullableBinding: ActivityMainBinding? = null
    private val binding get() = nullableBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nullableBinding = ActivityMainBinding.inflate(layoutInflater)
        supportFragmentManager.commit {
            replace<MenuFragment>(binding.fragmentContainer.id)
        }
        observeEffect()
        onBackPress {
            mainViewModel.sendEvent(MainActivityEvent.ToBack)
        }
        setContentView(binding.root)
    }

    private fun observeEffect() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            mainViewModel.effect.collect { effect ->
                when (effect) {
                    MainActivityEffect.ToBack -> supportFragmentManager.commit {
                        replace<MenuFragment>(binding.fragmentContainer.id)
                    }

                    MainActivityEffect.ToCompose -> supportFragmentManager.commit {
                        replace<ComposableFragment>(binding.fragmentContainer.id)
                    }

                    MainActivityEffect.ToXml -> supportFragmentManager.commit {
                        replace<XmlFragment>(binding.fragmentContainer.id)
                    }
                }
            }
        }
    }
}