package com.grappim.customviews.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grappim.customviews.utils.FragmentScreens

class MainViewModel(

) : ViewModel() {

    private val _screen = MutableLiveData<FragmentScreens>()
    val screen: LiveData<FragmentScreens>
        get() = _screen

    fun moveToScreen(fragmentsScreens: FragmentScreens) {
        _screen.value = fragmentsScreens
    }

}