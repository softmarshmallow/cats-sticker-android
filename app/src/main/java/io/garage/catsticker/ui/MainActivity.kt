package io.garage.catsticker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.garage.catsticker.R
import io.garage.catsticker.utils.GotoActivity
import com.google.firebase.firestore.FirebaseFirestore
import io.garage.catsticker.ui.photoeditor.EditImageActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "MainActivity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var go_editor_btn = findViewById<Button>(R.id.go_editor_btn)
        go_editor_btn.setOnClickListener {
            this.GotoActivity(EditImageActivity::class.java)
        }
    }

    // 으아아아아아


    // gs://cats-sticker.appspot.com/cats


    private fun retrieve() {
        val query = FirebaseFirestore.getInstance()
            .collection("cats")
            .orderBy("timestamp")
            .limit(50)
    }

}