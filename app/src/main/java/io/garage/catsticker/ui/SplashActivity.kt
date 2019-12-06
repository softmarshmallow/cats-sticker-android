package io.garage.catsticker.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.garage.catsticker.BuildConfig
import io.garage.catsticker.R
import io.garage.catsticker.ui.photoeditor.EditImageActivity
import io.garage.catsticker.utils.GotoActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setTheme(R.style.FullscreenTheme)
        goToMainActivityWhenReady()
    }


    private fun goToMainActivityWhenReady() {
        this.finish()
        this.GotoActivity(EditImageActivity::class.java)
    }

    private fun dev_runTestScripts() {
        if (BuildConfig.DEBUG){
        }
    }

    companion object {
        private val DELAY = 2000L
        private val DELAY_DEV = 1000L
    }

}
