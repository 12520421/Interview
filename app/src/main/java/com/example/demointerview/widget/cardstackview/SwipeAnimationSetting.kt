package com.example.demointerview.widget.cardstackview

import android.view.animation.AccelerateInterpolator
import android.view.animation.Interpolator
import com.example.demointerview.widget.cardstackview.internal.AnimationSetting

class SwipeAnimationSetting private constructor(
    override val direction: Direction,
    override val duration: Int,
    override val interpolator: Interpolator
) : AnimationSetting {
    class Builder {
        private var direction = Direction.Right
        private var duration = Duration.Normal.duration
        private var interpolator: Interpolator = AccelerateInterpolator()

        fun setDirection(direction: Direction): Builder {
            this.direction = direction
            return this
        }

        fun setDuration(duration: Int): Builder {
            this.duration = duration
            return this
        }

        fun setInterpolator(interpolator: Interpolator): Builder {
            this.interpolator = interpolator
            return this
        }

        fun build(): SwipeAnimationSetting {
            return SwipeAnimationSetting(
                direction,
                duration,
                interpolator
            )
        }
    }
}

