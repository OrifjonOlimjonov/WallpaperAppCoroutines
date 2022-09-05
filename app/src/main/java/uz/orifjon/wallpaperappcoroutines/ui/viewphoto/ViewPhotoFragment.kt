package uz.orifjon.wallpaperappcoroutines.ui.viewphoto

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.orifjon.wallpaperappcoroutines.R
import uz.orifjon.wallpaperappcoroutines.databinding.FragmentViewPhotoBinding
import uz.orifjon.wallpaperappcoroutines.models.Photo
import java.io.IOException

class ViewPhotoFragment : Fragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val viewById = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        viewById.visibility = View.INVISIBLE
    }

    private lateinit var binding:FragmentViewPhotoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPhotoBinding.inflate(inflater,container,false)

        val photo = requireArguments().getSerializable("photo") as Photo
         Picasso.get().load(photo.src.portrait).into(binding.imageView,object:Callback{
             override fun onSuccess() {
                  binding.progress.visibility = View.INVISIBLE
             }

             override fun onError(e: Exception?) {

             }

         })


        binding.btnSetDesktop.setOnClickListener { view ->
            binding.btnBack.visibility = View.INVISIBLE
            binding.btnDownload.visibility = View.INVISIBLE
            binding.btnShare.visibility = View.INVISIBLE
            binding.btnFilter.visibility = View.INVISIBLE
            binding.btnInfo.visibility = View.INVISIBLE
            binding.btnSetDesktop.visibility = View.INVISIBLE
            binding.btnLike.visibility = View.INVISIBLE
            binding.btnDesktop.visibility = View.VISIBLE
            binding.btnBlock.visibility = View.VISIBLE
            binding.btnBoth.visibility = View.VISIBLE
            binding.btnDesktopBack.visibility = View.VISIBLE
            binding.btnDesktop.setOnClickListener { view1 ->
                try {
                    val wallpaperManager =
                        WallpaperManager.getInstance(requireContext())
                    val bitmap =
                        (binding.imageView.background as BitmapDrawable).bitmap
                    wallpaperManager.setBitmap(bitmap)
                    Toast.makeText(requireContext(), "Set background image!", Toast.LENGTH_SHORT)
                        .show()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }


        binding.btnShare.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "text/plain"
            myIntent.type = "image/jpeg"
            myIntent.type = "audio/mpeg4-generic"
            myIntent.type = "text/html"
            myIntent.type = "audio/mpeg"
            myIntent.type = "audio/aac"
            myIntent.type = "audio/wav"
            myIntent.type = "audio/ogg"
            myIntent.type = "audio/midi"
            myIntent.type = "audio/x-ms-wma"
            myIntent.type = "video/mp4"
            myIntent.type = "video/x-msvideo"
            myIntent.type = "video/x-ms-wmv"
            myIntent.type = "image/png"
            myIntent.type = "image/jpeg"
            myIntent.type = "image/gif"
            myIntent.type = "text/xml"
            myIntent.type = "text/plain"
            myIntent.type = ".cfg -> text/plain"
            myIntent.type = ".csv -> text/plain"
            myIntent.type = ".conf -> text/plain"
            myIntent.type = ".rc -> text/plain"
            myIntent.type = ".htm -> text/html"
            myIntent.type = ".html -> text/html"
            myIntent.type = ".pdf -> application/pdf"
           // myIntent.putExtra(Intent.EXTRA_SUBJECT,  )
            startActivity(Intent.createChooser(myIntent, "Share Using"))
        }

        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }

        return binding.root
    }

   private fun setVisibility() {
        binding.btnBack.visibility = View.VISIBLE
        binding.btnDownload.visibility = View.VISIBLE
        binding.btnShare.visibility = View.VISIBLE
        binding.btnFilter.visibility = View.VISIBLE
        binding.btnInfo.visibility = View.VISIBLE
        binding.btnSetDesktop.visibility = View.VISIBLE
        binding.btnDesktopBack.visibility = View.VISIBLE
        binding.btnLike.visibility = View.VISIBLE
    }

   private fun setInvisibility() {
        binding.btnBack.visibility = View.INVISIBLE
        binding.btnDownload.visibility = View.INVISIBLE
        binding.btnShare.visibility = View.INVISIBLE
        binding.btnFilter.visibility = View.INVISIBLE
        binding.btnInfo.visibility = View.INVISIBLE
        binding.btnSetDesktop.visibility = View.INVISIBLE
        binding.btnDesktopBack.visibility = View.INVISIBLE
        binding.btnLike.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        val viewById = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        viewById.visibility = View.VISIBLE
        //binding.progress.visibility = View.VISIBLE
    }

}