package com.example.demointerview.widget.cardstackview.internal

import android.view.animation.Interpolator
import com.example.demointerview.widget.cardstackview.Direction

interface AnimationSetting {
    val direction: Direction
    val duration: Int
    val interpolator: Interpolator
}
