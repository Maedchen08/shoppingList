package com.example.shoppinglist8.components

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.shoppinglist8.R

class LoadingDialog {
    companion object {
        fun build(context: Context): androidx.appcompat.app.AlertDialog {
            val inflate = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null, true)
            val dialog = androidx.appcompat.app.AlertDialog.Builder(context).setView(inflate).setCancelable(true).create()
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            return dialog
        }
    }
}
