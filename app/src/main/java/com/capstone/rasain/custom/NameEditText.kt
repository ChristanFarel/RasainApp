package com.capstone.rasain.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.capstone.rasain.R

class NameEditText : AppCompatEditText, View.OnTouchListener {
    private lateinit var iconClear: Drawable
    private lateinit var iconPerson: Drawable

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
        hint = resources.getString(R.string.name_edit_text)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {
        iconClear = ContextCompat.getDrawable(context, R.drawable.ic_close_circle) as Drawable
        iconPerson = ContextCompat.getDrawable(context, R.drawable.ic_person) as Drawable
        setButtonDrawables(startOfTheText = iconPerson)

        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) showClearButton() else hideClearButton()
            }
            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })
    }

    private fun showClearButton() {
        setButtonDrawables(startOfTheText = iconPerson, endOfTheText = iconClear)
    }

    private fun hideClearButton() {
        setButtonDrawables(startOfTheText = iconPerson)
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
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
            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false
            when (layoutDirection) {
                View.LAYOUT_DIRECTION_RTL -> {
                    clearButtonEnd = (iconClear.intrinsicWidth + paddingStart).toFloat()
                    when {
                        event.x < clearButtonEnd -> isClearButtonClicked = true
                    }
                }
                else -> {
                    clearButtonStart = (width - paddingEnd - iconClear.intrinsicWidth).toFloat()
                    when {
                        event.x > clearButtonStart -> isClearButtonClicked = true
                    }
                }
            }
            when {
                isClearButtonClicked -> when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        iconClear = ContextCompat.getDrawable(context, R.drawable.ic_close_circle) as Drawable
                        showClearButton()
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        iconClear = ContextCompat.getDrawable(context, R.drawable.ic_close_circle) as Drawable
                        when {
                            text != null -> text?.clear()
                        }
                        hideClearButton()
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