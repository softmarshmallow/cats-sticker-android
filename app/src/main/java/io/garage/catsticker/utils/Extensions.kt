package io.garage.catsticker.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.net.Uri
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import io.garage.catsticker.App
import io.garage.catsticker.BuildConfig
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


fun AppCompatActivity.Toast(str : String) {
    android.widget.Toast.makeText(applicationContext, str, android.widget.Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.Toast(resId : Int) {
    android.widget.Toast.makeText(applicationContext, resId, android.widget.Toast.LENGTH_SHORT).show()
}

fun Fragment.Toast(str : String) {
    var c = context
    if(context == null){
        c = App.context
    }
    android.widget.Toast.makeText(c, str, android.widget.Toast.LENGTH_SHORT).show()
}

fun Fragment.Toast(resId : Int) {
    var c = context
    if(context == null){
        c = App.context
    }
    android.widget.Toast.makeText(c, resId, android.widget.Toast.LENGTH_SHORT).show()
}

fun Fragment.snack(str : String, v: View) {
    Snackbar.make(v, str, Snackbar.LENGTH_SHORT).show()
}

fun AppCompatActivity.GotoActivity(cls: Class<*>, @Nullable subscription: () -> Unit)  { var intent = Intent(this, cls)
    GotoActivity(cls)
    subscription()
}
fun AppCompatActivity.GotoActivity(cls: Class<*>)  { var intent = Intent(this, cls)
    this.startActivity(intent)
}

@SuppressLint("SimpleDateFormat")
fun Date.toHHmm(): String? {
    val transFormat = SimpleDateFormat("HH:mm")
    return transFormat.format(this)
}
@SuppressLint("SimpleDateFormat")
fun Date.toYYMMDD(): String? {
    val transFormat = SimpleDateFormat("yyyy.MM.dd")
    return transFormat.format(this)
}


fun Fragment.GotoActivity(cls: Class<*>) {
    var intent = Intent(activity, cls)
    this.startActivity(intent)
}

fun Any.getTAG (): String {
    return this.javaClass.simpleName
}

fun Any.Log(str: String){
    android.util.Log.i(this.getTAG(), str)
}

fun Any.debug(message: String) {
    if (BuildConfig.DEBUG)
        android.util.Log.d(this::class.java.simpleName, message)
}

fun Double.roundTo2DecimalPlaces() = BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()

fun View.createBitmapFromView(context: Context): Bitmap {
    val displayMetrics = DisplayMetrics()
    (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
    this.layoutParams =
            WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)

    this.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
    this.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
    this.buildDrawingCache()
    val bitmap = Bitmap.createBitmap(this.measuredWidth, this.measuredHeight, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(bitmap)
    this.draw(canvas)

    return bitmap
}

fun String.toLocation() : Location{
    var location = Location("")
    var parsedString = this.split(",")
    location.latitude = parsedString[0].toDouble()
    location.longitude = parsedString[1].toDouble()

    return location
}
fun Location.toFormedString() : String{
    var rtn = ""
    rtn = "${latitude}, ${longitude}"
    return rtn

}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: androidx.lifecycle.Observer<T>) {
    this.observe(lifecycleOwner, object : androidx.lifecycle.Observer<T> {
        override fun onChanged(t: T) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

fun <T> MutableLiveData<T>.forceRefresh() {
    this.value = this.value
}

fun Int.secondToMunuite(): String{
    val v = this / 60
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.HALF_UP
    return df.format(v)
}

fun Int.currencyFormatWithComma(): String {
    return NumberFormat.getNumberInstance().format(this)
}

/**
 * CONTEXT
 */

// CLipboard
fun Context.clipboard(): ClipboardManager = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

fun Context.clearClipboard() = apply { clipboard().setPrimaryClip(ClipData.newPlainText("", "")) }

fun Context.hasItemInClipboard(): Boolean = clipboard().primaryClip != null

fun Context.openPlayStore() {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
    } catch (anfe: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            )
        )
    }
}

