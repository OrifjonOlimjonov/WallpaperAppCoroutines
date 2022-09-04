package uz.orifjon.wallpaperappcoroutines.models

import androidx.room.*

@Dao
interface PhotoDao {


    @Insert
    fun add(photo: PhotoSave)

    @Update
    fun update(photo: PhotoSave)

    @Delete
    fun delete(photo: PhotoSave)

    @Query("SELECT * FROM photo_save")
    fun list():List<PhotoSave>

}