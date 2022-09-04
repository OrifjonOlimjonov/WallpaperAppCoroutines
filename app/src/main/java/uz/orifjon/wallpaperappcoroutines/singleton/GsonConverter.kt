package uz.orifjon.wallpaperappcoroutines.singleton

import com.google.gson.Gson

object GsonConverter {

    private var gson: Gson? = null

    fun getInstance():Gson{
        if (gson == null) {
            gson = Gson()
        }
        return gson as Gson
    }

}