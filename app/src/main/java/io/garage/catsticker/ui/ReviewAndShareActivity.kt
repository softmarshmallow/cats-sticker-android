package io.garage.catsticker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_review_and_share.*
import android.os.Environment.getExternalStorageDirectory
import android.content.Intent
import android.provider.MediaStore
import android.net.Uri
import android.util.Log
import io.garage.catsticker.R
import java.util.*


class ReviewAndShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_and_share)
        initUI()
    }

    fun initUI(){
        buttonShare.setOnClickListener {
            shareToInstagram()
        }
    }

    private fun shareToInstagram(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
