package uz.orifjon.wallpaperappcoroutines.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.orifjon.wallpaperappcoroutines.models.database.Photo
import uz.orifjon.wallpaperappcoroutines.retrofit.ApiClient
import uz.orifjon.wallpaperappcoroutines.retrofit.ApiService
import java.lang.Exception

class PhotosPaging3Library(private val query:String) : PagingSource<Int, Photo>() {
    private val apiService = ApiClient.getRetrofit().create(ApiService::class.java)


    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
            return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            var nextPageNumber = params.key ?: 1
            val response = apiService.getPhotoList(nextPageNumber.toString(), "18", query)
            if (response.isSuccessful) {
                LoadResult.Page(response.body()?.photos ?: emptyList(), null, ++nextPageNumber)
            } else {
                LoadResult.Error(Throwable("Error"))
            }
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}