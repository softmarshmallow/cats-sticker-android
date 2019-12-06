package io.garage.catsticker.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE


class MockDatabase(val context: Context){

    private val SHARED_PREF_NAME = "LOCAL_PREFERENCES"
    private val IS_PREMIUM_KEY_NAME = "IS_PREMIUM_KEY"
    var isPremium: Boolean
    get() {
        val sp = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        return sp.getBoolean(IS_PREMIUM_KEY_NAME, false)
    }
    set(value) {
        val sp = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        val editor = sp.edit()
        editor.putBoolean(IS_PREMIUM_KEY_NAME, value)
        editor.apply()
    }
}