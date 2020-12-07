package com.example.vtorectbeakerview

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.RectF
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color

val colors : Array<Int> = arrayOf(
    "#F44336",
    "#009688",
    "#3F51B5",
    "#4CAF50",
    "#2196F3"
).map {
    Color.parseColor(it)
}.toTypedArray()
val parts : Int = 4
val scGap : Float = 0.02f / parts
val strokeFactor : Float = 90f
val delay : Long = 20
val sizeFactor : Float = 4.9f
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawVToRectBeaker(scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = Math.min(w, h) / sizeFactor
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, parts)
    val sf2 : Float = sf.divideScale(1, parts)
    val sf3 : Float = sf.divideScale(2, parts)
    val x : Float = size * 0.5f * sf1
    val y : Float = -size * sf2
    save()
    translate(w / 2, h / 2)
    for (j in 0..1) {
        save()
        scale(1f - 2 * j, 1f)
        drawLine(0f, 0f, x, 0f, paint)
        drawLine(0f, 0f, 0f, y, paint)
        restore()
    }
    drawRect(RectF(-size / 2, -size * sf3, size / 2, 0f), paint)
    restore()
}

fun Canvas.drawVTRBNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h)  / strokeFactor
    drawVToRectBeaker(scale, w, h, paint)
}

class VToRectBeakerView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}