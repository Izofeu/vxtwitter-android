package pl.izofeu.linkfix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when {
            intent?.action == Intent.ACTION_SEND -> handleSendText(intent)
        }
    }

    private fun handleSendText(intent: Intent)
    {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            var readytext = it
            when {
                readytext.contains("vxtwitter.com") -> return
            }
            when {
                readytext.contains("twitter.com") -> readytext = readytext.replace("twitter.com", "c.vxtwitter.com")
            }
            val sendIntent: Intent = Intent().apply {
                type = "text/plain"
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, readytext)
                // setClassName("org.telegram.messenger.web", "org.telegram.ui.ExternalActionActivity")
            }

            val shareIntent = Intent.createChooser(sendIntent, readytext)
            startActivity(shareIntent)
        }
    }
}
