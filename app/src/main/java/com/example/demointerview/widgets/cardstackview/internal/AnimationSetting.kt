package com.example.demointerview.widgets.cardstackview.internal

import android.view.animation.Interpolator
import com.example.demointerview.widgets.cardstackview.Direction

interface AnimationSetting {
    val direction: Direction
    val duration: Int
    val interpolator: Interpolator
}
