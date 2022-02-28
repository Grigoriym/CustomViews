package com.grappim.customviews.ui.settings_item_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.grappim.customviews.R
import com.grappim.customviews.databinding.ViewSettingsItemBinding
import com.grappim.customviews.utils.delegate.viewProperty
import com.grappim.customviews.utils.onCheckChanged

class SettingsItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    companion object {
        private const val DEFAULT_TITLE_TEXT_COLOR = Color.BLACK
        private const val DEFAULT_DESCRIPTION_TEXT_COLOR = Color.GRAY
        private const val DEFAULT_TITLE_TEXT_SIZE = 20f
    }

    private val binding: ViewSettingsItemBinding = ViewSettingsItemBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    var switchListener: ((isChecked: Boolean) -> Unit)? = null

    private var isDescriptionTextInvisible: Boolean = false

    @get:ColorInt
    var titleTextColor: Int by viewProperty(DEFAULT_TITLE_TEXT_COLOR)

    var titleTextSize: Float by viewProperty(DEFAULT_TITLE_TEXT_SIZE)

    var isSwitchVisible: Boolean by viewProperty(false)
    var isSwitchEnabled: Boolean by viewProperty(true)

    var isTitleBold: Boolean = true
        set(value) {
            field = value
            if (value) {
                if (isTitleItalic) {
                    binding.titleTextView.setTypeface(null, Typeface.BOLD_ITALIC)
                } else {
                    binding.titleTextView.setTypeface(binding.titleTextView.typeface, Typeface.BOLD)
                }
            } else {
                if (isTitleItalic) {
                    binding.titleTextView.setTypeface(null, Typeface.ITALIC)
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
                    binding.titleTextView.setTypeface(null, Typeface.BOLD_ITALIC)
                } else {
                    binding.titleTextView.setTypeface(
                        binding.titleTextView.typeface,
                        Typeface.ITALIC
                    )
                }
            } else {
                if (isTitleBold) {
                    binding.titleTextView.setTypeface(null, Typeface.BOLD)
                } else {
                    setTitleTextNormal()
                }
            }
        }

    init {
        setWillNotDraw(false)
        binding.onOffSwitch.onCheckChanged {
            switchListener?.invoke(it)
        }

        context.withStyledAttributes(attrs, R.styleable.SettingsItemView) {
            titleTextColor = getInt(
                R.styleable.SettingsItemView_siw_titleTextColor,
                titleTextColor
            )
            titleTextSize = getDimension(
                R.styleable.SettingsItemView_siw_titleTextSize,
                titleTextSize
            )

            isSwitchVisible = getBoolean(
                R.styleable.SettingsItemView_siw_isSwitchVisible,
                isSwitchVisible
            )
            isSwitchEnabled = getBoolean(
                R.styleable.SettingsItemView_siw_isSwitchEnabled,
                isSwitchEnabled
            )

            binding.titleTextView.apply {
                text = getString(R.styleable.SettingsItemView_siw_titleText)

                isTitleBold = getBoolean(
                    R.styleable.SettingsItemView_siw_isTitleBold,
                    false
                )
                isTitleItalic = getBoolean(
                    R.styleable.SettingsItemView_siw_isTitleItalic,
                    false
                )
            }

            binding.descriptionTextView.apply {
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

        }
        hideDescriptionViewIfNoContent()
    }

    override fun onDraw(canvas: Canvas?) {
        binding.titleTextView.setTextColor(titleTextColor)
        binding.titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTextSize)

        binding.onOffSwitch.isVisible = isSwitchVisible
        binding.onOffSwitch.isEnabled = isSwitchEnabled

        hideDescriptionViewIfNoContent()
        super.onDraw(canvas)
    }

    fun setTitleTextNormal() {
        binding.titleTextView.setTypeface(null, Typeface.NORMAL)
    }

    fun setTitleText(@StringRes resId: Int) {
        binding.titleTextView.setText(resId)
    }

    fun setTitleText(text: String) {
        binding.titleTextView.text = text
    }

    fun setDescriptionText(@StringRes resId: Int) {
        binding.descriptionTextView.setText(resId)
        hideDescriptionViewIfNoContent()
    }

    fun setDescriptionText(text: String) {
        binding.descriptionTextView.text = text
        hideDescriptionViewIfNoContent()
    }

    private fun hideDescriptionViewIfNoContent() {
        binding.descriptionTextView.apply {
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
        switchListener = null
    }
}