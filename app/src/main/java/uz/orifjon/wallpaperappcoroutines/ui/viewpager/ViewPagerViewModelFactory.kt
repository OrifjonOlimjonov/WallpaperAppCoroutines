package uz.orifjon.wallpaperappcoroutines.ui.viewpager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewPagerViewModelFactory(val query:String):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewPagerViewModel::class.java)){
            return ViewPagerViewModel(query) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}