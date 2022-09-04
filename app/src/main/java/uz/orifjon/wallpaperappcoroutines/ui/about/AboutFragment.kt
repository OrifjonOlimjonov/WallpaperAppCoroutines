package uz.orifjon.wallpaperappcoroutines.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uz.orifjon.wallpaperappcoroutines.R
import uz.orifjon.wallpaperappcoroutines.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var binding:FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater,container,false)

        val viewModel = ViewModelProvider(this)[AboutViewModel::class.java]

        viewModel.setList(binding.tv.text.toString())

        viewModel.getList().observe(viewLifecycleOwner
        ) {
            binding.tv.text = it
        }



        return binding.root
    }

}