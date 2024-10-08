package com.example.demointerview.widgets.cardstackview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.example.demointerview.widgets.cardstackview.internal.CardStackDataObserver
import com.example.demointerview.widgets.cardstackview.internal.CardStackSnapHelper

class CardStackView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(
    context!!, attrs, defStyle
) {
    private val observer: CardStackDataObserver = CardStackDataObserver(this)

    init {
        initialize()
    }

    override fun setLayoutManager(manager: LayoutManager?) {
        if (manager is CardStackLayoutManager) {
            super.setLayoutManager(manager)
        } else {
            throw IllegalArgumentException("CardStackView must be set CardStackLayoutManager.")
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        if (layoutManager == null) {
            layoutManager = CardStackLayoutManager(context)
        }
        if (getAdapter() != null) {
            getAdapter()!!.unregisterAdapterDataObserver(observer)
            getAdapter()!!.onDetachedFromRecyclerView(this)
        }
        adapter?.registerAdapterDataObserver(observer)
        super.setAdapter(adapter)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            (layoutManager as CardStackLayoutManager?)?.updateProportion(event.y)
        }
        return super.onInterceptTouchEvent(event)
    }

    fun swipe() {
        if (layoutManager is CardStackLayoutManager) {
            val manager: CardStackLayoutManager = layoutManager as CardStackLayoutManager
            smoothScrollToPosition(manager.topPosition + 1)
        }
    }

    fun rewind() {
        if (layoutManager is CardStackLayoutManager) {
            val manager: CardStackLayoutManager = layoutManager as CardStackLayoutManager
            smoothScrollToPosition(manager.topPosition - 1)
        }
    }

    private fun initialize() {
        CardStackSnapHelper().attachToRecyclerView(this)
        overScrollMode = OVER_SCROLL_NEVER
    }
}
