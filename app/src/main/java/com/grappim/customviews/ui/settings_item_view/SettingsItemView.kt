package com.grappim.customviews.ui.settings_item_view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textview.MaterialTextView
import com.grappim.customviews.R
import com.grappim.customviews.utils.onCheckChanged

class SettingsItemView(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    companion object {
        private const val DEFAULT_TITLE_TEXT_COLOR = Color.BLACK
        private const val DEFAULT_DESCRIPTION_TEXT_COLOR = Color.GRAY
    }

    private var _switchListener: ((isChecked: Boolean) -> Unit)? = null
    private val switchListener: (isChecked: Boolean) -> Unit
        get() = requireNotNull(_switchListener)

    private val titleTextView: MaterialTextView
    private val descriptionTextView: MaterialTextView
    private val switch: SwitchMaterial

    private var isDescriptionTextInvisible: Boolean = false

    var isTitleBold: Boolean = true
        set(value) {
            field = value
            val asd = titleTextView.typeface
            if (value) {
                if (isTitleItalic) {
                    titleTextView.setTypeface(null, Typeface.BOLD_ITALIC)
                } else {
                    titleTextView.setTypeface(titleTextView.typeface, Typeface.BOLD)
                }
            } else {
                if (isTitleItalic) {
                    titleTextView.setTypeface(null, Typeface.ITALIC)
                } else {
                    setTitleTextNormal()
                }
            }
        }

    var isTitleItalic: Boolean = false
        set(value) {
            field = value
            if (value) {
                if (isTitleBold) {
                    titleTextView.setTypeface(null, Typeface.BOLD_ITALIC)
                } else {
                    titleTextView.setTypeface(titleTextView.typeface, Typeface.ITALIC)
                }
            } else {
                if (isTitleBold) {
                    titleTextView.setTypeface(null, Typeface.BOLD)
                } else {
                    setTitleTextNormal()
                }
            }
        }

    init {
        inflate(context, R.layout.view_settings_item, this)

        titleTextView = findViewById(R.id.titleTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        switch = findViewById(R.id.onOffSwitch)

        switch.onCheckChanged {
            switchListener(it)
        }

        context.withStyledAttributes(attrs, R.styleable.SettingsItemView) {
            titleTextView.apply {
                text = getString(R.styleable.SettingsItemView_siw_titleText)
                val textColor = getInt(
                    R.styleable.SettingsItemView_siw_titleTextColor,
                    DEFAULT_TITLE_TEXT_COLOR
                )
                setTextColor(textColor)

                val isBold = getBoolean(
                    R.styleable.SettingsItemView_siw_isTitleBold,
                    false
                )
                val isItalic = getBoolean(
                    R.styleable.SettingsItemView_siw_isTitleItalic,
                    false
                )
                isTitleBold = isBold
                isTitleItalic = isItalic

                val textSize = getDimension(
                    R.styleable.SettingsItemView_siw_titleTextSize,
                    resources.getDimension(R.dimen.siw_default_title_text_size)
                )
                setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
            }

            descriptionTextView.apply {
                text = getString(R.styleable.SettingsItemView_siw_descriptionText)
                val textColor = getInt(
                    R.styleable.SettingsItemView_siw_descriptionTextColor,
                    DEFAULT_DESCRIPTION_TEXT_COLOR
                )
                setTextColor(textColor)

                isDescriptionTextInvisible = getBoolean(
                    R.styleable.SettingsItemView_siw_descriptionTextInvisible,
                    false
                )
            }

            val showSwitch = getBoolean(
                R.styleable.SettingsItemView_siw_showSwitch,
                false
            )
            switch.isVisible = showSwitch

        }
        hideDescriptionViewIfNoContent()
    }

    fun setTitleTextNormal() {
        titleTextView.setTypeface(null, Typeface.NORMAL)
    }

    fun setTitleTextSize(value: Float) {
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, value)

    }

    fun setSwitchListener(callback: (isChecked: Boolean) -> Unit) {
        _switchListener = callback
    }

    fun setTitleText(@StringRes resId: Int) {
        titleTextView.setText(resId)
    }

    fun setTitleText(text: String) {
        titleTextView.text = text
    }

    fun setTitleTextColor(@ColorInt idRes: Int) {
        titleTextView.setTextColor(idRes)
    }

    fun setDescriptionText(@StringRes resId: Int) {
        descriptionTextView.setText(resId)
        hideDescriptionViewIfNoContent()
    }

    fun setDescriptionText(text: String) {
        descriptionTextView.text = text
        hideDescriptionViewIfNoContent()
    }

    fun setSwitchEnabled(enabled: Boolean) {
        switch.isChecked = enabled
    }

    private fun hideDescriptionViewIfNoContent() {
        descriptionTextView.apply {
            visibility = if (text.isNullOrEmpty()) {
                if (isDescriptionTextInvisible) {
                    INVISIBLE
                } else {
                    GONE
                }
            } else {
                VISIBLE
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _switchListener = null
    }
}