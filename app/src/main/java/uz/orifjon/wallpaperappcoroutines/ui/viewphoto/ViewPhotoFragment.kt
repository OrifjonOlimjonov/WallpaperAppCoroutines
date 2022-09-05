package uz.orifjon.wallpaperappcoroutines.ui.viewphoto

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.uvstudio.him.photofilterlibrary.PhotoFilter
import uz.orifjon.wallpaperappcoroutines.R
import uz.orifjon.wallpaperappcoroutines.adapters.AdapterRecyclerView
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
    private lateinit var originalImage: Bitmap

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPhotoBinding.inflate(inflater, container, false)
        val photoFilter = PhotoFilter()

        val photo = requireArguments().getSerializable("photo") as Photo
        Picasso.get().load(photo.src.portrait).into(binding.imageView, object : Callback {
            override fun onSuccess() {
                binding.progress.visibility = View.INVISIBLE
                originalImage = (binding.imageView.drawable as BitmapDrawable).bitmap
                binding.rvFilter.adapter =
                    AdapterRecyclerView(requireContext(), resources, photo) { photo, position ->
                        val bitmap = (binding.imageView.drawable as BitmapDrawable).bitmap
                        when (position) {
                            0 -> {
                               binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.one(context, bitmap))
                            }
                            1 ->{
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.two(context, bitmap))
                            }
                            2 -> {
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.three(context, bitmap))
                            }
                            3 ->{
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.four(context, bitmap))
                            }
                            4 -> {
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.five(context, bitmap))
                            }
                            5 -> {
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.six(context, bitmap))
                            }
                            6 -> {
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.seven(context, bitmap))
                            }
                            7 -> {
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.eight(context, bitmap))
                            }
                            8 -> {
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.nine(context, bitmap))
                            }
                            9 -> {
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.ten(context, bitmap))
                            }
                            10 -> {
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.eleven(context, bitmap))
                            }
                            11 -> {
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.twelve(context, bitmap))
                            }
                            12 -> {
                                binding.imageView.setImageBitmap(originalImage)
                                binding.imageView.setImageBitmap(photoFilter.thirteen(context, bitmap))
                            }
                        }
                    }
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setBitmap(
                            bitmap,
                            null,
                            false,
                            WallpaperManager.FLAG_SYSTEM
                        )
                    }
                    Toast.makeText(requireContext(), "Set home screen image!", Toast.LENGTH_SHORT)
                        .show()
                    //TODO : Ekranga set qilishni yozish kerak
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            binding.btnBlock.setOnClickListener {
                try {
                    val wallpaperManager = WallpaperManager.getInstance(requireContext())
                    val bitmap = (binding.imageView.drawable as BitmapDrawable).bitmap
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK)
                    }
                    Toast.makeText(requireContext(), "Set lock screen image!", Toast.LENGTH_SHORT)
                        .show()
                    //TODO : Ekranga set qilishni yozish kerak
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            binding.btnBoth.setOnClickListener {
                try {
                    val wallpaperManager = WallpaperManager.getInstance(requireContext())
                    val bitmap = (binding.imageView.drawable as BitmapDrawable).bitmap
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setBitmap(
                            bitmap, null, false,
                            WallpaperManager.FLAG_LOCK or WallpaperManager.FLAG_SYSTEM
                        )
                    }
                    Toast.makeText(requireContext(), "Set both screen image!", Toast.LENGTH_SHORT)
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
            binding.tvAverageColor.text = "Average Color: ${photo.avg_color}"
            binding.tvTheme.text = "Theme: ${photo.alt}"
            binding.tvImagePhotograph.text = "Photograph: ${photo.photographer}"
            binding.tvImageSize.text = "Size: ${photo.width}x${photo.height}"
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
//            val myIntent = Intent(Intent.ACTION_SEND)
//            myIntent.type = "text/plain"
//            myIntent.type = "audio/mpeg4-generic"
//            myIntent.type = "text/html"
//            myIntent.type = "audio/mpeg"
//            myIntent.type = "audio/aac"
//            myIntent.type = "audio/wav"
//            myIntent.type = "audio/ogg"
//            myIntent.type = "audio/midi"
//            myIntent.type = "audio/x-ms-wma"
//            myIntent.type = "video/mp4"
//            myIntent.type = "video/x-msvideo"
//            myIntent.type = "video/x-ms-wmv"
//            myIntent.type = "image/png"
//            myIntent.type = "image/jpeg"
//            myIntent.type = "image/gif"
//            myIntent.type = "text/xml"
//            myIntent.type = "text/plain"
//            myIntent.type = ".cfg -> text/plain"
//            myIntent.type = ".csv -> text/plain"
//            myIntent.type = ".conf -> text/plain"
//            myIntent.type = ".rc -> text/plain"
//            myIntent.type = ".htm -> text/html"
//            myIntent.type = ".html -> text/html"
//            myIntent.type = ".pdf -> application/pdf"
//             myIntent.putExtra(Intent.EXTRA_SUBJECT, photo )
//            startActivity(Intent.createChooser(myIntent, "Share Using"))

//            val bitmap = (binding.imageView.drawable as BitmapDrawable).bitmap
//            val icon: Bitmap = bitmap
//            val share = Intent(Intent.ACTION_SEND)
//            share.type = "image/jpeg"
//            val bytes = ByteArrayOutputStream()
//            icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//            val f = File(
//                Environment.getExternalStorageDirectory()
//                    .toString() + File.separator + "temporary_file.jpg"
//            )
//            try {
//                f.createNewFile()
//                val fo = FileOutputStream(f)
//                fo.write(bytes.toByteArray())
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//
//
//            startActivity(Intent.createChooser(share, "Share Image"))


            val bitmap = (binding.imageView.drawable as BitmapDrawable).bitmap
            shareImageandText(bitmap)
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
            binding.imageView.setImageBitmap(originalImage)
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
        val viewById =
            requireActivity().findViewById<BottomNavigationView>(uz.orifjon.wallpaperappcoroutines.R.id.bottomNavigation)
        viewById.visibility = View.VISIBLE
        //binding.progress.visibility = View.VISIBLE
    }


    private fun shareImageandText(bitmap: Bitmap) {
        val uri = getmageToShare(bitmap)
        val intent = Intent(Intent.ACTION_SEND)

        intent.putExtra(Intent.EXTRA_STREAM, uri)

        intent.putExtra(Intent.EXTRA_TEXT, "Sharing Image")

        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")

        intent.type = "image/png"

        startActivity(Intent.createChooser(intent, "Share Via"))
    }

    private fun getmageToShare(bitmap: Bitmap): Uri? {
        val imageFolder: File = File("images")
        var uri: Uri? = null
        try {
            imageFolder.mkdirs()
            val file = File(imageFolder, "shared_image.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream)
            outputStream.flush()
            outputStream.close()
            uri = FileProvider.getUriForFile(
                requireContext(),
                "com.anni.shareimage.fileprovider",
                file
            )
        } catch (e: java.lang.Exception) {
            // Toast.makeText(this, "" + e.message, Toast.LENGTH_LONG).show()
        }
        return uri
    }

}