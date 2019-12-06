package io.garage.catsticker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.garage.catsticker.R
import kotlinx.android.synthetic.main.activity_cat_photo_editor.*
import ja.burhanrashid52.photoeditor.PhotoEditor
import android.graphics.Typeface
import android.R.attr.font
import androidx.core.content.res.ResourcesCompat



class CatPhotoEditorActivity : AppCompatActivity() {
    private lateinit var mPhotoEditor: PhotoEditor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_photo_editor)
        initUi()
        initPhotoEditor()
    }

    private fun initUi(){
        photoEditorView.source.setImageResource(R.drawable.ic_remove)
    }

    private fun initPhotoEditor(){
//        val mTextRobotoTf = ResourcesCompat.getFont(this, R.font.roboto_medium)
//        val mEmojiTypeFace = Typeface.createFromAsset(assets, "emojione-android.ttf")
        mPhotoEditor = PhotoEditor.Builder(this, photoEditorView)
            .setPinchTextScalable(true)
//            .setDefaultTextTypeface(mTextRobotoTf)
//            .setDefaultEmojiTypeface(mEmojiTypeFace)
            .build()

        mPhotoEditor.setBrushDrawingMode(true)
    }
}
