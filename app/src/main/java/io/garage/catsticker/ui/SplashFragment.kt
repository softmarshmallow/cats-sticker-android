package io.garage.catsticker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.garage.catsticker.R


class SplashFragment : Fragment() {
    var mPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPage = if (arguments != null) arguments!!.getInt("num") else 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (mPage == 0) {
            return inflater.inflate(R.layout.fragment_splash_1, container, false)
        } else if (mPage == 1) {
            return inflater.inflate(R.layout.fragment_splash_2, container, false)
        } else {
            return inflater.inflate(R.layout.fragment_splash_3, container, false)
        }

    }
}