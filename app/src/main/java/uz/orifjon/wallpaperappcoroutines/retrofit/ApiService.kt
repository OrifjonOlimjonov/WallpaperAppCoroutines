package uz.orifjon.wallpaperappcoroutines.retrofit

import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.orifjon.wallpaperappcoroutines.models.PhotosApi

interface ApiService {

    @GET("/v1/search/?")
    fun getList(@Query("page") page: String,@Query("per_page") per_page: String,@Query("query") query:String): Flowable<PhotosApi>

    @GET("/v1/search/?")
    suspend fun getPhotoList(@Query("page") page: String,@Query("per_page") per_page: String,@Query("query") query:String): Response<PhotosApi>


}