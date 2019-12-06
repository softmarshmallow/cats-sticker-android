package io.garage.catsticker.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import io.garage.catsticker.R
import io.garage.catsticker.utils.MockDatabase
import kotlinx.android.synthetic.main.dialog_go_premium.*

class GoPremiumDialog(val parent: Context): Dialog(parent) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_go_premium)
        initUI()
    }

    private fun initUI(){
        buttonClose.setOnClickListener{
            dismiss()
        }

        buttonGoPremium.setOnClickListener{
            // set premium is enabled
            MockDatabase(context).isPremium = true
            dismiss()
        }
    }
}