package uz.orifjon.wallpaperappcoroutines.ui.viewphoto

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import uz.orifjon.wallpaperappcoroutines.databinding.FragmentViewPagerBinding

class ViewPhotoFragment : Fragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    private lateinit var binding:FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(inflater,container,false)



        return binding.root
    }

}