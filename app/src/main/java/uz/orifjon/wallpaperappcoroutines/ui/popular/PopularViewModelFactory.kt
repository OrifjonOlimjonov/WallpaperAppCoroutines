package uz.orifjon.wallpaperappcoroutines.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PopularViewModelFactory(var query:String):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PopularViewModel::class.java)){
            return PopularViewModel(query) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}