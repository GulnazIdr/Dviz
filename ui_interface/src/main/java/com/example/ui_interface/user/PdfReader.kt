package com.example.ui_interface.user

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri

class PdfReader (
    private val context: Context
) {
    fun readPdf(url: String){
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(url.toUri(), "application/pdf")
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }

        with(context) {
            try {
                startActivity(intent)
            }catch (e: ActivityNotFoundException){
                Log.e("Activity not found", "${e.message} ${e::class.simpleName}")
                Toast.makeText(context, "PDF not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}