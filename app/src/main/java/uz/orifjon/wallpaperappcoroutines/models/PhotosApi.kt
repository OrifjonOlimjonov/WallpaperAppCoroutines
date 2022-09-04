package uz.orifjon.wallpaperappcoroutines.models

data class PhotosApi(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)