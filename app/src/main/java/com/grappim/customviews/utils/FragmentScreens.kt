package com.grappim.customviews.utils

sealed class FragmentScreens {
    abstract val clearBackStack: Boolean
    abstract val fromFragment: String?

    data class Main(
        override val clearBackStack: Boolean = false,
        override val fromFragment: String? = null
    ) : FragmentScreens()

    data class MusicPlayerEqualizer(
        override val clearBackStack: Boolean = false,
        override val fromFragment: String? = null
    ) : FragmentScreens()

    data class Chart(
        override val clearBackStack: Boolean = false,
        override val fromFragment: String? = null
    ) : FragmentScreens()

    data class FittingToolbar(
        override val clearBackStack: Boolean = false,
        override val fromFragment: String? = null
    ) : FragmentScreens()

    data class SettingsItems(
        override val clearBackStack: Boolean = false,
        override val fromFragment: String? = null
    ) : FragmentScreens()

    data class ProgressBar(
        override val clearBackStack: Boolean = false,
        override val fromFragment: String? = null
    ) : FragmentScreens()

    data class WaveView(
        override val clearBackStack: Boolean = false,
        override val fromFragment: String? = null
    ) : FragmentScreens()

    data class CircularTextView(
        override val clearBackStack: Boolean = false,
        override val fromFragment: String? = null
    ) : FragmentScreens()

    data class CustomTabLayout(
        override val clearBackStack: Boolean = false,
        override val fromFragment: String? = null
    ) : FragmentScreens()
}