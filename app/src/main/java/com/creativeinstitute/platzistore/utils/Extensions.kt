package com.creativeinstitute.platzistore.utils

import android.widget.ImageView
import coil3.load
import coil3.request.CachePolicy
import coil3.request.placeholder
import com.creativeinstitute.platzistore.R

fun ImageView.load(imageUrl:String){

    this.load(imageUrl){
        placeholder(R.drawable.baseline_face_retouching_natural_24)
        error(R.drawable.baseline_no_accounts_24)
        diskCachePolicy(CachePolicy.ENABLED)
    }


}