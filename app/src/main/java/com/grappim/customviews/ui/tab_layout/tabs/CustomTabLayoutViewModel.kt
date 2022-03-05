package com.grappim.customviews.ui.tab_layout.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grappim.customviews.ui.tab_layout.CustomTabItem

class CustomTabLayoutViewModel(

) : ViewModel() {

    private val _currentScreen = MutableLiveData<CustomTabItem>(CustomTabItem.FIRST)
    val currentScreen: LiveData<CustomTabItem>
        get() = _currentScreen

    fun setNextScreen(customTabItem: CustomTabItem) {
        _currentScreen.value = customTabItem
    }

    fun setNextScreen(position: Int) {
        _currentScreen.value = CustomTabItem.values()[position]
    }

    fun onBackPressed() {
        _currentScreen.value = CustomTabItem.getItemOnBackPressed(_currentScreen.value)
    }

}