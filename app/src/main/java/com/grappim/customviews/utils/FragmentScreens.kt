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

    data class Third(
        override val clearBackStack: Boolean = false,
        override val fromFragment: String? = null
    ) : FragmentScreens()
}