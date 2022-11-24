package com.example.finalproject.extensions

import android.widget.ImageView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.finalproject.R
import java.util.*

//fun ImageView.loadUrl(url: String?, isCircleCrop: Boolean = false) {
//
//
//    url?.let {
//        this.load(it) {
//            crossfade(true)
//            placeholder(R.mipmap.ic_launcher2)
//            if(isCircleCrop) transformations(CircleCropTransformation())
//        }
//    }
//}

fun String.titlecase(): String =
    this.replaceFirstChar { // it: Char
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else
            it.toString()
    }