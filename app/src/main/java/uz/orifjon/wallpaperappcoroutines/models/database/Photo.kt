package uz.orifjon.wallpaperappcoroutines.models.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.orifjon.wallpaperappcoroutines.models.Src

@Entity
data class Photo(
    val alt: String,
    val avg_color: String,
    val height: Int,
    @PrimaryKey
    val id: Int,
    val liked: Boolean,
    val photographer: String,
    val photographer_id: Int,
    val photographer_url: String,
    val src: Src,
    val url: String?,
    val width: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readParcelable(Src::class.java.classLoader)!!,
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(alt)
        parcel.writeString(avg_color)
        parcel.writeInt(height)
        parcel.writeInt(id)
        parcel.writeByte(if (liked) 1 else 0)
        parcel.writeString(photographer)
        parcel.writeInt(photographer_id)
        parcel.writeString(photographer_url)
        parcel.writeParcelable(src, flags)
        parcel.writeString(url)
        parcel.writeInt(width)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }


}