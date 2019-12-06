package io.garage.catsticker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.ListFragment
import androidx.viewpager.widget.ViewPager
import io.garage.catsticker.BuildConfig
import io.garage.catsticker.R
import io.garage.catsticker.ui.photoeditor.EditImageActivity
import io.garage.catsticker.utils.GotoActivity
import kotlinx.android.synthetic.main.activity_splash.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

class SplashActivity : AppCompatActivity() {
    private lateinit var mPager: ViewPager
    private val NUM_SPLASH_PAGES = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppCompat_NoActionBar)
        setContentView(R.layout.activity_splash)

        mPager = findViewById(io.garage.catsticker.R.id.splash_viewpager)

        val pagerAdapter = SplashPagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter

        go_main_btn.setOnClickListener {
            goToMainActivityWhenReady()
        }
    }

    private fun goToMainActivityWhenReady() {
        this.GotoActivity(EditImageActivity::class.java)
        this.finish()
    }

    private inner class SplashPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = NUM_SPLASH_PAGES

        override fun getItem(position: Int): Fragment {
            val f = SplashFragment()
            val args = Bundle()
            args.putInt("num", position)
            f.arguments = args

            return f
        }
    }

    private fun dev_runTestScripts() {
        if (BuildConfig.DEBUG) {
        }
    }

    companion object {
        private val DELAY = 2000L
        private val DELAY_DEV = 1000L
    }

}
