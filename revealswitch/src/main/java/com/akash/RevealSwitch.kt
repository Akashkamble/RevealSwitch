package com.akash

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.support.annotation.ColorInt
import android.support.annotation.IntRange
import android.util.AttributeSet
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import com.akash.revealswitch.OnToggleListener
import com.akash.revealswitch.R

class RevealSwitch @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private lateinit var unCheckedView: View
    private lateinit var checkedView: View
    private lateinit var checkedThumb: View
    private lateinit var unCheckedThumb: View
    private lateinit var clickable: View
    private lateinit var revealSwitchContainer: FrameLayout
    private var isEnable: Boolean = false
    private lateinit var border: GradientDrawable
    private var isborderEnabled: Boolean = false
    private lateinit var typedArray: TypedArray

    private var enabledTrackColor: Int = Color.parseColor("#444444")
    private var disabledTrackColor: Int = Color.parseColor("#FFFFFF")
    private var animDuration: Int = 500
    private var borderColor: Int? = null

    init {
        View.inflate(context, R.layout.layout_revealswitch, this).apply {
            checkedView = findViewById<View>(R.id.checkedView)
            unCheckedView = findViewById<View>(R.id.unCheckedView)
            unCheckedThumb = findViewById<View>(R.id.unCheckedThumb)
            checkedThumb = findViewById<View>(R.id.checkedThumb)
            clickable = findViewById(R.id.clickable)
            revealSwitchContainer = findViewById(R.id.revealSwitch_Container)
            border = clickable.background as GradientDrawable
            getAttributeSet(context, attrs)

        }
    }

    private fun getAttributeSet(context: Context, attributeSet: AttributeSet?) {
        typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RevealSwitch)
        enabledTrackColor = typedArray.getColor(R.styleable.RevealSwitch_setEnabledTrackColor,
                Color.parseColor("#444444"))
        disabledTrackColor = typedArray.getColor(R.styleable.RevealSwitch_setDisabledTrackColor,
                Color.parseColor("#FFFFFF"))
        isEnable = typedArray.getBoolean(R.styleable.RevealSwitch_setEnabled, false)
        animDuration = typedArray.getInteger(R.styleable.RevealSwitch_setAnimationDuration,
                500)
        isborderEnabled = typedArray.getBoolean(R.styleable.RevealSwitch_showBorder, false)
        borderColor = typedArray.getColor(
                R.styleable.RevealSwitch_borderColor,
                0
        )
        if (animDuration > 1500 || animDuration < 500) {
            throw IllegalArgumentException("duration must be between 500 to 1500")
        } else {
            setAnimationDuration(animDuration)
        }
        setAnimationDuration(animDuration)
        setEnable(isEnable)
        setEnabledTrackColor(enabledTrackColor)
        setDisabledTrackColor(disabledTrackColor)
        typedArray.recycle()
    }

    fun setAnimationDuration(@SuppressLint("SupportAnnotationUsage") @IntRange(
            from = 500,
            to = 1500
    ) duration: Int) {
        if (duration > 1500 || duration < 500) {
            throw IllegalArgumentException("duration must be between 500 to 1500")
        } else {
            animDuration = duration
        }
    }

    fun setDisabledTrackColor(@ColorInt trackColor: Int) {
        disabledTrackColor = trackColor
        unCheckedView.background.setColorFilter(trackColor, PorterDuff.Mode.SRC_ATOP)
        checkedThumb.background.setColorFilter(trackColor, PorterDuff.Mode.SRC_ATOP)
    }

    fun setEnabledTrackColor(@ColorInt trackColor: Int) {
        enabledTrackColor = trackColor
        checkedView.background.setColorFilter(trackColor, PorterDuff.Mode.SRC_ATOP)
        unCheckedThumb.background.setColorFilter(trackColor, PorterDuff.Mode.SRC_ATOP)
    }

    fun setToggleListener(onToggleListener: OnToggleListener) {
        clickable.setOnClickListener {
            if (isEnable) {
                onToggleListener.onToggle(false)
                revealDisableTrackAnimation()
            } else {
                revealEnableTrackAnimation()
                onToggleListener.onToggle(true)
            }
        }
    }

    private fun revealEnableTrackAnimation() {
        isEnable = true
        unCheckedView.visibility = View.INVISIBLE
        showBorderIfEnabled(disabledTrackColor, borderColor)
        revealSwitchContainer.background.setColorFilter(disabledTrackColor, PorterDuff.Mode.SRC_ATOP)
        checkedView.visibility = View.VISIBLE
        val x: Int = unCheckedThumb.left + unCheckedThumb.width / 2
        val y: Int = revealSwitchContainer.height / 2
        val endRadius: Double = Math.hypot(revealSwitchContainer.width.toDouble(), revealSwitchContainer.height.toDouble())
        val startRadius = 0
        val anim = ViewAnimationUtils.createCircularReveal(checkedView, x, y, startRadius.toFloat(), endRadius.toFloat())
        anim.duration = animDuration.toLong()
        anim.start()
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationCancel(animation: Animator?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                revealSwitchContainer.background.setColorFilter(enabledTrackColor, PorterDuff.Mode.SRC_ATOP)
            }
        })
    }

    private fun revealDisableTrackAnimation() {
        isEnable = false
        checkedView.visibility = View.INVISIBLE
        showBorderIfEnabled(enabledTrackColor, borderColor)
        revealSwitchContainer.background.setColorFilter(enabledTrackColor, PorterDuff.Mode.SRC_ATOP)
        unCheckedView.visibility = View.VISIBLE
        val x: Int = checkedThumb.right - checkedThumb.width / 2
        val y: Int = revealSwitchContainer.height / 2
        val endRadius = 0
        val startRadius = Math.hypot(revealSwitchContainer.width.toDouble(), revealSwitchContainer.height.toDouble())
        val anim = ViewAnimationUtils.createCircularReveal(unCheckedView, x, y, endRadius.toFloat(), startRadius.toFloat())
        anim.duration = animDuration.toLong()
        anim.start()
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationEnd(animation: Animator?) {
                revealSwitchContainer.background.setColorFilter(disabledTrackColor, PorterDuff.Mode.SRC_ATOP)
            }

            override fun onAnimationCancel(animation: Animator?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationStart(animation: Animator?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun setEnable(isChecked: Boolean) {
        isEnable = isChecked
        if (isEnable) {
            unCheckedView.visibility = View.INVISIBLE
            showBorderIfEnabled(disabledTrackColor, borderColor)
        } else {
            unCheckedView.visibility = View.VISIBLE
            showBorderIfEnabled(enabledTrackColor, borderColor)
        }
    }

    private fun showBorderIfEnabled(color: Int, borderColor: Int?) {
        if (isborderEnabled) {
            if (borderColor == 0) {
                border.setStroke(4, color)
            } else {
                border.setStroke(4, borderColor!!)
            }
        } else {
            border.setStroke(0, color)
        }
    }
}