package uz.orifjon.wallpaperappcoroutines.ui.history

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.orifjon.wallpaperappcoroutines.R
import uz.orifjon.wallpaperappcoroutines.blurlayout.BlurLayout
import uz.orifjon.wallpaperappcoroutines.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
      //  (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val viewById = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val viewById1 = requireActivity().findViewById<BlurLayout>(R.id.blur)
        viewById.visibility = View.INVISIBLE
        viewById1.visibility = View.INVISIBLE
    }

    private lateinit var binding:FragmentHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)

        val viewById = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        viewById.visibility = View.INVISIBLE



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewById = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        viewById.visibility = View.INVISIBLE
    }
    override fun onDestroy() {
        super.onDestroy()
     //   (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        val viewById = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val viewById1 = requireActivity().findViewById<BlurLayout>(R.id.blur)
        viewById.visibility = View.VISIBLE
        viewById1.visibility = View.VISIBLE
    }
}