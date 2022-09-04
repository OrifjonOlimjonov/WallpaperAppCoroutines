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
            fun onBind(photo: Photo){
                Picasso.get().load(photo.src.small).into(binding.imgRV,object:Callback{
                    override fun onSuccess() {
                      binding.progress.visibility = View.INVISIBLE
                        binding.imgRV.visibility = View.VISIBLE
                    }

                    override fun onError(e: Exception?) {

                    }

                })

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
            holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}