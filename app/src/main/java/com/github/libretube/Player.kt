package com.github.libretube

import android.app.Activity
import android.os.Bundle
import com.google.android.material.color.DynamicColors

class Player : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
    }
}
