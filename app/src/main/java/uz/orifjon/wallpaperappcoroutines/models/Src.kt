package uz.orifjon.wallpaperappcoroutines.models

import android.os.Parcel
import android.os.Parcelable

data class Src(
    val landscape: String,
    val large: String,
    val large2x: String,
    val medium: String,
    val original: String,
    val portrait: String,
    val small: String,
    val tiny: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(landscape)
        p0?.writeString(large)
        p0?.writeString(large2x)
        p0?.writeString(medium)
        p0?.writeString(original)
        p0?.writeString(portrait)
        p0?.writeString(small)
        p0?.writeString(tiny)
    }

    companion object CREATOR : Parcelable.Creator<Src> {
        override fun createFromParcel(parcel: Parcel): Src {
            return Src(parcel)
        }

        override fun newArray(size: Int): Array<Src?> {
            return arrayOfNulls(size)
        }
    }
}