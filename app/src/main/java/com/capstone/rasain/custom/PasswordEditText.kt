package com.capstone.rasain.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.capstone.rasain.R

class PasswordEditText : AppCompatEditText, View.OnTouchListener {
    private lateinit var iconPassword: Drawable
    private lateinit var iconEye: Drawable
    private lateinit var iconEyeSlash: Drawable
    private var isEyeClick: Boolean = false


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = "Masukkan nama Anda"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
//        inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    private fun init() {
        iconEye = ContextCompat.getDrawable(context, R.drawable.ic_eye) as Drawable
        iconEyeSlash = ContextCompat.getDrawable(context, R.drawable.ic_eye_slash) as Drawable
        iconPassword = ContextCompat.getDrawable(context, R.drawable.ic_key) as Drawable

        hidePassword()

        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                hidePassword()
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Do nothing.
            }
            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })
    }

    private fun hidePassword() {
        setButtonDrawables(startOfTheText = iconPassword, endOfTheText = iconEye)
    }

    private fun showPassword() {
        setButtonDrawables(startOfTheText = iconPassword, endOfTheText = iconEyeSlash)
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText:Drawable? = null,
        endOfTheText:Drawable? = null,
        bottomOfTheText: Drawable? = null
    ){
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val eyeButtonStart: Float
            val eyeButtonEnd: Float
            var isEyeButtonClicked = false
            when (layoutDirection) {
                View.LAYOUT_DIRECTION_RTL -> {
                    eyeButtonEnd = (iconEye.intrinsicWidth + paddingStart).toFloat()
                    when {
                        event.x < eyeButtonEnd -> isEyeButtonClicked = true
                    }
                }
                else -> {
                    eyeButtonStart = (width - paddingEnd - iconEye.intrinsicWidth).toFloat()
                    when {
                        event.x > eyeButtonStart -> isEyeButtonClicked = true
                    }
                }
            }
            when {
                isEyeButtonClicked -> when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        iconEye = ContextCompat.getDrawable(context, R.drawable.ic_eye) as Drawable
                        iconEyeSlash = ContextCompat.getDrawable(context, R.drawable.ic_eye_slash) as Drawable
                        isEyeClick = true
                        inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        showPassword()
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        iconEye = ContextCompat.getDrawable(context, R.drawable.ic_eye) as Drawable
                        iconEyeSlash = ContextCompat.getDrawable(context, R.drawable.ic_eye_slash) as Drawable
                        isEyeClick = false
                        inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                        hidePassword()
                        return true
                    }
                    else -> return false
                }
                else -> return false
            }
        }
        return false
    }
}