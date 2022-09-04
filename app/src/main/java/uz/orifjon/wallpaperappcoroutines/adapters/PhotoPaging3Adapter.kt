package uz.orifjon.wallpaperappcoroutines.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import retrofit2.Callback
import uz.orifjon.wallpaperappcoroutines.databinding.ItemRvBinding
import uz.orifjon.wallpaperappcoroutines.models.Photo
import java.lang.Exception

class PhotoPaging3Adapter:PagingDataAdapter<Photo,PhotoPaging3Adapter.DataVH>(MyDiffUtil()) {

    class MyDiffUtil:DiffUtil.ItemCallback<Photo>(){
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

    }

    inner class DataVH(var binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(photo: Photo){
            binding.apply {
                Picasso.get().load(photo.src.small).into(imgRV,object:com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        binding.progress.visibility = View.INVISIBLE
                        binding.imgRV.visibility = View.VISIBLE
                    }

                    override fun onError(e: Exception?) {

                    }

                })
            }
        }
    }

    override fun onBindViewHolder(holder: DataVH, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.onBind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataVH {
        return DataVH(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}