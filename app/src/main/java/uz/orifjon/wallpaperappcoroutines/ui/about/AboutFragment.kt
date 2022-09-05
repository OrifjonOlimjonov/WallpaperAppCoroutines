package uz.orifjon.wallpaperappcoroutines.ui.about

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.orifjon.wallpaperappcoroutines.R
import uz.orifjon.wallpaperappcoroutines.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
       // (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val viewById = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        viewById.visibility = View.INVISIBLE
    }

    private lateinit var binding:FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater,container,false)
        val viewById = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        viewById.visibility = View.INVISIBLE
        val viewModel = ViewModelProvider(this)[AboutViewModel::class.java]

        viewModel.setList(binding.tv.text.toString())

        viewModel.getList().observe(viewLifecycleOwner) {
            binding.tv.text = it
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewById = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        viewById.visibility = View.INVISIBLE
    }
    override fun onDestroy() {
        super.onDestroy()
        //(activity as AppCompatActivity?)!!.supportActionBar!!.show()
        val viewById = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        viewById.visibility = View.VISIBLE
    }

}