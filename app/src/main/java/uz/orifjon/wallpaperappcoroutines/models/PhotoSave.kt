package uz.orifjon.wallpaperappcoroutines.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_save")
data class PhotoSave(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val photo:String
)