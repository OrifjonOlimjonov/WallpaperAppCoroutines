package uz.orifjon.wallpaperappcoroutines.ui.random

import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class RandomViewModelFactory(private val query:String):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(RandomViewModel::class.java)){
           return RandomViewModel(query) as T
       }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}