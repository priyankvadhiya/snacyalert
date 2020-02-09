package com.priyank.snacyalert

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import kotlinx.android.synthetic.main.alert_view.view.*

class AlertView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), Animation.AnimationListener{

    private var enterAnimation: Animation =
        AnimationUtils.loadAnimation(context, R.anim.slide_in_from_top)
    private var exitAnimation: Animation =
        AnimationUtils.loadAnimation(context, R.anim.slide_out_to_top)

    internal var duration = 1000L

    private var runningAnimation: Runnable? = null

    init {
        View.inflate(context, R.layout.alert_view, this)
        ViewCompat.setTranslationZ(this, Int.MAX_VALUE.toFloat())
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        enterAnimation.setAnimationListener(this)
        animation = enterAnimation
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        enterAnimation.setAnimationListener(null)
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
        startHideAnimation()
    }

    override fun onAnimationStart(animation: Animation?) {

    }

    private fun startHideAnimation() {
        runningAnimation = Runnable { hide() }
        postDelayed(runningAnimation, duration)
    }

    private fun hide() {
        try {
            exitAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    removeFromParent()
                }

                override fun onAnimationStart(animation: Animation?) {

                }
            })
            startAnimation(exitAnimation)
        } catch (e: Exception) {
            Log.e(javaClass.name, Log.getStackTraceString(e))
        }
    }

    private fun removeFromParent() {
        clearAnimation()
        visibility = View.GONE

        postDelayed(object : Runnable {
            override fun run() {
                try {
                    if (parent != null) {
                        try {
                            (parent as ViewGroup).removeView(this@AlertView)
                        } catch (e: Exception) {
                            Log.e(javaClass.simpleName, Log.getStackTraceString(e))
                        }
                    }
                } catch (e: Exception) {
                    Log.e(javaClass.simpleName, Log.getStackTraceString(e))
                }
            }
        }, 100)
    }

    fun setAlertBackgroundColor(@ColorInt color: Int) {
        alertCard.setCardBackgroundColor(color)
    }

    fun setTitle(@StringRes titleId: Int) {
        setTitle(context.getString(titleId))
    }

    fun setText(@StringRes textId: Int) {
        setText(context.getString(textId))
    }

    fun setTitle(title: CharSequence) {
        if (!TextUtils.isEmpty(title)) {
            txtTitle?.visibility = View.VISIBLE
            txtTitle?.text = title
        }
    }

    fun setText(text: CharSequence) {
        if (!TextUtils.isEmpty(text)) {
            txtMessage?.visibility = View.VISIBLE
            txtMessage?.text = text
        }
    }

    fun setTitleTypeface(typeface: Typeface) {
        txtTitle?.typeface = typeface
    }

    fun setTextTypeface(typeface: Typeface) {
        txtMessage?.typeface = typeface
    }

    fun setIcon(@DrawableRes iconId: Int) {
        img?.setImageDrawable(AppCompatResources.getDrawable(context, iconId))
    }

    fun setIcon(drawable: Drawable) {
        img?.setImageDrawable(drawable)
    }

    fun showIcon(showIcon: Boolean) {
        if (showIcon) {
            img?.visibility = View.VISIBLE
        }
    }
}