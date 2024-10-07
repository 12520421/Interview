package com.example.demointerview.widgets.cardstackview.internal

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.demointerview.widgets.cardstackview.CardStackLayoutManager
import com.example.demointerview.widgets.cardstackview.Duration
import com.example.demointerview.widgets.cardstackview.SwipeAnimationSetting
import kotlin.math.abs

class CardStackSnapHelper : SnapHelper() {
    private var velocityX = 0
    private var velocityY = 0

    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray {
        if (layoutManager is CardStackLayoutManager) {
            if (layoutManager.findViewByPosition(layoutManager.topPosition) != null) {
                val x = targetView.translationX.toInt()
                val y = targetView.translationY.toInt()
                if (x != 0 || y != 0) {
                    val setting: CardStackSetting = layoutManager.cardStackSetting
                    val horizontal = (abs(x.toDouble()) / targetView.width.toFloat()).toFloat()
                    val vertical = (abs(y.toDouble()) / targetView.height.toFloat()).toFloat()
                    val duration =
                        Duration.fromVelocity(if (velocityY < velocityX) velocityX else velocityY)
                    if (duration == Duration.Fast || setting.swipeThreshold < horizontal || setting.swipeThreshold < vertical) {
                        val state: CardStackState = layoutManager.cardStackState
                        if (setting.directions.contains(state.direction)) {
                            state.targetPosition = state.topPosition + 1

                            val swipeAnimationSetting = SwipeAnimationSetting.Builder()
                                .setDirection(setting.swipeAnimationSetting.direction)
                                .setDuration(duration.duration)
                                .setInterpolator(setting.swipeAnimationSetting.interpolator)
                                .build()
                            layoutManager.setSwipeAnimationSetting(swipeAnimationSetting)

                            this.velocityX = 0
                            this.velocityY = 0

                            val scroller = CardStackSmoothScroller(
                                CardStackSmoothScroller.ScrollType.ManualSwipe,
                                layoutManager
                            )
                            scroller.targetPosition = layoutManager.topPosition
                            layoutManager.startSmoothScroll(scroller)
                        } else {
                            val scroller = CardStackSmoothScroller(
                                CardStackSmoothScroller.ScrollType.ManualCancel,
                                layoutManager
                            )
                            scroller.targetPosition = layoutManager.topPosition
                            layoutManager.startSmoothScroll(scroller)
                        }
                    } else {
                        val scroller = CardStackSmoothScroller(
                            CardStackSmoothScroller.ScrollType.ManualCancel,
                            layoutManager
                        )
                        scroller.targetPosition = layoutManager.topPosition
                        layoutManager.startSmoothScroll(scroller)
                    }
                }
            }
        }
        return IntArray(2)
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        if (layoutManager is CardStackLayoutManager) {
            val view = layoutManager.findViewByPosition(layoutManager.topPosition)
            if (view != null) {
                val x = view.translationX.toInt()
                val y = view.translationY.toInt()
                if (x == 0 && y == 0) {
                    return null
                }
                return view
            }
        }
        return null
    }

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager,
        velocityX: Int,
        velocityY: Int
    ): Int {
        this.velocityX = abs(velocityX.toDouble()).toInt()
        this.velocityY = abs(velocityY.toDouble()).toInt()
        if (layoutManager is CardStackLayoutManager) {
            return layoutManager.topPosition
        }
        return RecyclerView.NO_POSITION
    }
}
