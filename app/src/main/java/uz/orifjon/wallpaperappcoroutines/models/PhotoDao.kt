package uz.orifjon.wallpaperappcoroutines.models

import androidx.room.*

@Dao
interface PhotoDao {


    @Insert
    fun add(photo: Photo)

    @Update
    fun update(photo: Photo)

    @Delete
    fun delete(photo: Photo)

    @Query("SELECT * FROM Photo where id = :id")
    fun getImage(id:Int):Photo?

    @Query("SELECT * FROM Photo")
    fun list():List<Photo>

}