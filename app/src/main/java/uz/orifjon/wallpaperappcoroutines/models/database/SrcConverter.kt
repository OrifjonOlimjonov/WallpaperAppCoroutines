package uz.orifjon.wallpaperappcoroutines.models.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import uz.orifjon.wallpaperappcoroutines.models.Src

class SrcConverter {
    @TypeConverter
    fun fromSrc(src: Src): String {
        return Gson().toJson(src)
    }

    @TypeConverter
    fun toSrc(srcString: String): Src {
        return Gson().fromJson(srcString, Src::class.java)
    }
}