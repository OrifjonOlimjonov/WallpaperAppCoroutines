package uz.orifjon.wallpaperappcoroutines.ui.viewphoto

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.orifjon.wallpaperappcoroutines.R
import uz.orifjon.wallpaperappcoroutines.databinding.CustomDialogBinding
import uz.orifjon.wallpaperappcoroutines.databinding.FragmentViewPhotoBinding
import uz.orifjon.wallpaperappcoroutines.models.Photo
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class ViewPhotoFragment : Fragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val viewById = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        viewById.visibility = View.INVISIBLE
    }

    private lateinit var binding: FragmentViewPhotoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPhotoBinding.inflate(inflater, container, false)

        val photo = requireArguments().getSerializable("photo") as Photo
        Picasso.get().load(photo.src.portrait).into(binding.imageView, object : Callback {
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

                    val wallpaperManager = WallpaperManager.getInstance(requireContext())
                    val bitmap = (binding.imageView.drawable as BitmapDrawable).bitmap
                    wallpaperManager.setBitmap(bitmap)
                    Toast.makeText(requireContext(), "Set background image!", Toast.LENGTH_SHORT)
                        .show()
                    //TODO : Ekranga set qilishni yozish kerak
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }



        binding.btnBackFilter.setOnClickListener { view ->
            setVisibility()
            binding.rvFilter.visibility = View.INVISIBLE
            binding.seekbarFilter.visibility = View.INVISIBLE
            binding.btnBackFilter.visibility = View.INVISIBLE
            binding.btnOKFilter.visibility = View.INVISIBLE
        }


        binding.btnInfo.setOnClickListener { view ->
            binding.btnBack.visibility = View.INVISIBLE
            binding.btnDownload.visibility = View.INVISIBLE
            binding.btnShare.visibility = View.INVISIBLE
            binding.btnFilter.visibility = View.INVISIBLE
            binding.btnInfo.visibility = View.INVISIBLE
            binding.btnSetDesktop.visibility = View.INVISIBLE
            binding.btnDesktopBack.visibility = View.INVISIBLE
            binding.btnLike.visibility = View.INVISIBLE
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            val binding: CustomDialogBinding = CustomDialogBinding.inflate(layoutInflater)
            bottomSheetDialog.setContentView(binding.root)
            bottomSheetDialog.setCancelable(true)
            bottomSheetDialog.show()
            bottomSheetDialog.setOnCancelListener { setVisibility() }
//            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
//            alertDialog.setView(binding.root)
//            val alertDialog1 = alertDialog.create()
//            val window: Window? = alertDialog1.window
//            val wlp: WindowManager.LayoutParams = window!!.attributes
//            wlp.gravity = Gravity.BOTTOM
//            wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
//            window.attributes = wlp
//            alertDialog1.window!!.setBackgroundDrawableResource(android.R.color.transparent)
//            alertDialog1.show()
        }







        binding.btnDownload.setOnClickListener { view ->
            val image: Drawable = binding.imageView.drawable
            if (image is BitmapDrawable) {
                val drawable = image as BitmapDrawable
                val bitmap = drawable.bitmap
                try {
                    val file =
                        File(File.pathSeparator.lowercase(Locale.getDefault()))
                    val stream = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    stream.flush()
                    stream.close()
                } catch (e: java.lang.Exception) {
                    Toast.makeText(
                        requireContext(),
                        "Not save, path address error entered!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }








        binding.btnDesktopBack.setOnClickListener { view ->
            binding.btnBack.visibility = View.VISIBLE
            binding.btnDownload.visibility = View.VISIBLE
            binding.btnShare.visibility = View.VISIBLE
            binding.btnFilter.visibility = View.VISIBLE
            binding.btnInfo.visibility = View.VISIBLE
            binding.btnSetDesktop.visibility = View.VISIBLE
            binding.btnLike.visibility = View.VISIBLE
            binding.btnDesktop.visibility = View.INVISIBLE
            binding.btnBlock.visibility = View.INVISIBLE
            binding.btnBoth.visibility = View.INVISIBLE
            binding.btnDesktopBack.visibility = View.INVISIBLE
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










        binding.btnLike.setOnClickListener { view ->
            var k = true
            var index = -1
//            if (list.get(i).isLike()) {
//                binding.btnLike.setBackgroundResource(R.drawable.dislike)
//            } else {
//                binding.btnLike.setBackgroundResource(R.drawable.like)
//            }
//
//            if (k) {
//                list.add(Like(img.getImage(), true))
//                binding.btnLike.setBackgroundResource(R.drawable.like)
//            } else {
//                list.removeAt(index)
//            }

        }





        binding.btnFilter.setOnClickListener { view ->
          //  loadList()
            setInvisibility()
            binding.btnOKFilter.visibility = View.VISIBLE
            binding.btnBackFilter.visibility = View.VISIBLE
        //    val adapter = RecyclarViewFilterAdapter(listAdapter)
       //     binding.rvFilter.adapter = adapter
            binding.rvFilter.visibility = View.VISIBLE
            binding.seekbarFilter.visibility = View.VISIBLE
        }
        binding.btnBackFilter.setOnClickListener { view ->
            setVisibility()
            binding.rvFilter.visibility = View.INVISIBLE
            binding.seekbarFilter.visibility = View.INVISIBLE
            binding.btnBackFilter.visibility = View.INVISIBLE
            binding.btnOKFilter.visibility = View.INVISIBLE
        }









        binding.btnBack.setOnClickListener {
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