package uz.orifjon.wallpaperappcoroutines.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import uz.orifjon.wallpaperappcoroutines.paging3.PhotosPaging3Library

class HomeViewModel(private var query:String): ViewModel() {
    val flow = Pager(
        PagingConfig(18)
    ){
        PhotosPaging3Library(query)
    }.flow.cachedIn(viewModelScope)
}