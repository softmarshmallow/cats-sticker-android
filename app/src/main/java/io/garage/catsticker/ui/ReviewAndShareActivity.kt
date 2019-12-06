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
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream


class ReviewAndShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_and_share)
        initUI()
    }

    private fun initUI(){
        buttonShare.setOnClickListener {
            shareToInstagram()
        }
    }

    private fun shareToInstagram(){

        val b = BitmapFactory.decodeResource(resources, R.drawable.got)
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg"
        val bytes = ByteArrayOutputStream()
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            contentResolver,
            b, "Title", null
        )
        val imageUri = Uri.parse(path)
        share.putExtra(Intent.EXTRA_STREAM, imageUri)
        startActivity(Intent.createChooser(share, "Select"))


        /*
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)*/
    }
}
