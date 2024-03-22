package uz.orifjon.wallpaperappcoroutines.adapters

import android.content.ClipData.Item
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.uvstudio.him.photofilterlibrary.PhotoFilter
import uz.orifjon.wallpaperappcoroutines.databinding.ItemRvBinding
import uz.orifjon.wallpaperappcoroutines.models.database.Photo
import java.lang.Exception

class RecyclerViewAdapterLiked(var context: Context, val itemClick: (Photo, Int) -> Unit) :
    ListAdapter<Photo, RecyclerViewAdapterLiked.MyViewHolder>(MyDiffUtils()) {

    class MyDiffUtils : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

    class MyViewHolder(var binding: ItemRvBinding) : ViewHolder(binding.root) {
        fun onBind(photo: Photo, position: Int) {
            Picasso.get().load(photo.src.small).into(binding.imgRV, object : Callback {
                override fun onSuccess() {
                    binding.progress.visibility = View.INVISIBLE
                    binding.imgRV.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                }

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val photo = getItem(position)
        holder.onBind(photo, position)
        holder.itemView.setOnClickListener {
            itemClick(photo, position)
        }
    }

}