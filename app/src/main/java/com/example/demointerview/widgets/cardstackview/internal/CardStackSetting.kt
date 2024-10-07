package com.example.demointerview.widgets.cardstackview.internal

import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.example.demointerview.widgets.cardstackview.Direction
import com.example.demointerview.widgets.cardstackview.RewindAnimationSetting
import com.example.demointerview.widgets.cardstackview.StackFrom
import com.example.demointerview.widgets.cardstackview.SwipeAnimationSetting
import com.example.demointerview.widgets.cardstackview.SwipeableMethod

class CardStackSetting {
    var stackFrom: StackFrom = StackFrom.None
    var visibleCount: Int = 3
    var translationInterval: Float = 8.0f
    var scaleInterval: Float = 0.95f // 0.0f - 1.0f
    var swipeThreshold: Float = 0.3f // 0.0f - 1.0f
    var maxDegree: Float = 20.0f
    var directions: List<Direction> = Direction.HORIZONTAL
    var canScrollHorizontal: Boolean = true
    var canScrollVertical: Boolean = true
    var swipeableMethod: SwipeableMethod = SwipeableMethod.AutomaticAndManual
    var swipeAnimationSetting: SwipeAnimationSetting = SwipeAnimationSetting.Builder().build()
    var rewindAnimationSetting: RewindAnimationSetting = RewindAnimationSetting.Builder().build()
    var overlayInterpolator: Interpolator = LinearInterpolator()
}
