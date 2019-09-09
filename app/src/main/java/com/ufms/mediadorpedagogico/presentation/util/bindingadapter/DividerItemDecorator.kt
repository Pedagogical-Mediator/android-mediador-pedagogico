package com.ufms.mediadorpedagogico.presentation.util.bindingadapter

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class DividerItemDecorator(private val mDivider: Drawable) : RecyclerView.ItemDecoration() {

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0..parent.childCount - 2) {
            val boundaries = getBoundaries(parent.getChildAt(i), parent)
            with(boundaries) {
                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(canvas)
            }
        }
    }

    private fun getBoundaries(child: View, parent: RecyclerView): DividerBoundaries {
        val params = (child.layoutParams as RecyclerView.LayoutParams)
        val left = parent.paddingLeft
        val top = child.bottom + params.bottomMargin
        val right = parent.width - parent.paddingRight
        val bottom = top + mDivider.intrinsicHeight
        return DividerBoundaries(left, top, right, bottom)
    }

    private data class DividerBoundaries(
        var left: Int = 0,
        var top: Int = 0,
        var right: Int = 0,
        var bottom: Int = 0
    )
}