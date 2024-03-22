package uz.orifjon.wallpaperappcoroutines.adapters

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.uvstudio.him.photofilterlibrary.PhotoFilter
import uz.orifjon.wallpaperappcoroutines.R
import uz.orifjon.wallpaperappcoroutines.databinding.ItemRvBinding
import uz.orifjon.wallpaperappcoroutines.models.Photo
import java.lang.Exception
import kotlin.coroutines.coroutineContext

class AdapterRecyclerView(var context: Context, var resources: Resources, var photo:Photo,var itemClick:(Photo,Int)->Unit) :
    RecyclerView.Adapter<AdapterRecyclerView.VH>() {

    inner class VH(var binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val photoFilter = PhotoFilter()
            Picasso.get().load(photo.src.small).into(binding.imgRV, object : Callback {
                override fun onSuccess() {
                    binding.progress.visibility = View.INVISIBLE
                    binding.imgRV.visibility = View.VISIBLE
                    val bitmap = (binding.imgRV.drawable as BitmapDrawable).bitmap

                    when (position) {
                        0 -> binding.imgRV.setImageBitmap(photoFilter.one(context, bitmap))
                        1 -> binding.imgRV.setImageBitmap(photoFilter.two(context, bitmap))
                        2 -> binding.imgRV.setImageBitmap(photoFilter.three(context, bitmap))
                        3 -> binding.imgRV.setImageBitmap(photoFilter.four(context, bitmap))
                        4 -> binding.imgRV.setImageBitmap(photoFilter.five(context, bitmap))
                        5 -> binding.imgRV.setImageBitmap(photoFilter.six(context, bitmap))
                        6 -> binding.imgRV.setImageBitmap(photoFilter.seven(context, bitmap))
                        7 -> binding.imgRV.setImageBitmap(photoFilter.eight(context, bitmap))
                        8 -> binding.imgRV.setImageBitmap(photoFilter.nine(context, bitmap))
                        9 -> binding.imgRV.setImageBitmap(photoFilter.ten(context, bitmap))
                        10 -> binding.imgRV.setImageBitmap(photoFilter.eleven(context, bitmap))
                        11 -> binding.imgRV.setImageBitmap(photoFilter.twelve(context, bitmap))
                        12 -> binding.imgRV.setImageBitmap(photoFilter.thirteen(context, bitmap))
                    }
                }

                override fun onError(e: Exception?) {

                }

            })


                itemView.setOnClickListener {
                    itemClick(photo,position)
                }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = 13

}