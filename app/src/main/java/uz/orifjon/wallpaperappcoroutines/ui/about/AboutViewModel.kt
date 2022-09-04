package uz.orifjon.wallpaperappcoroutines.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel:ViewModel() {

    val list = MutableLiveData<String>()


    fun getList():LiveData<String>{
        return list
    }

    fun setList(about:String){
        list.postValue(about)
    }
}