package uz.orifjon.wallpaperappcoroutines.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.orifjon.wallpaperappcoroutines.databinding.ItemRvBinding
import uz.orifjon.wallpaperappcoroutines.models.Photo
import java.lang.Exception
import kotlin.coroutines.coroutineContext

class AdapterRecyclerView(var list: List<Photo>): RecyclerView.Adapter<AdapterRecyclerView.VH>() {


    inner class VH(var binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
            fun onBind(photo: Photo,position: Int){
                Picasso.get().load(photo.src.small).into(binding.imgRV,object:Callback{
                    override fun onSuccess() {
                      binding.progress.visibility = View.INVISIBLE
                        binding.imgRV.visibility = View.VISIBLE
                    }

                    override fun onError(e: Exception?) {

                    }

                })

                when (position) {
                    1 -> binding.imgRV.setImageBitmap(photoFilter.one(context, myBitmap1))
                    2 -> binding.imgRV.setImageBitmap(photoFilter.two(context, myBitmap1))
                    3 -> binding.imgRV.setImageBitmap(photoFilter.three(context, myBitmap1))
                    4 -> binding.imgRV.setImageBitmap(photoFilter.four(context, myBitmap1))
                    5 -> binding.imgRV.setImageBitmap(photoFilter.five(context, myBitmap1))
                    6 -> binding.imgRV.setImageBitmap(photoFilter.six(context, myBitmap1))
                    7 -> binding.imgRV.setImageBitmap(photoFilter.seven(context, myBitmap1))
                    8 -> binding.imgRV.setImageBitmap(photoFilter.eight(context, myBitmap1))
                    9 -> binding.imgRV.setImageBitmap(photoFilter.nine(context, myBitmap1))
                    10 -> binding.imgRV.setImageBitmap(photoFilter.ten(context, myBitmap1))
                    11 -> binding.imgRV.setImageBitmap(photoFilter.eleven(context, myBitmap1))
                    12 -> binding.imgRV.setImageBitmap(photoFilter.twelve(context, myBitmap1))
                    13 -> binding.imgRV.setImageBitmap(photoFilter.thirteen(context, myBitmap1))
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
            holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size

}