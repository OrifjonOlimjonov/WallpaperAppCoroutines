package uz.orifjon.wallpaperappcoroutines.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.orifjon.wallpaperappcoroutines.adapters.PhotoPaging3Adapter
import uz.orifjon.wallpaperappcoroutines.databinding.FragmentPopularBinding
import uz.orifjon.wallpaperappcoroutines.models.Photo
import uz.orifjon.wallpaperappcoroutines.ui.random.RandomViewModel
import uz.orifjon.wallpaperappcoroutines.ui.random.RandomViewModelFactory
import kotlin.coroutines.CoroutineContext

class PopularFragment : Fragment(),CoroutineScope {

    private var _binding: FragmentPopularBinding? = null

    private val binding get() = _binding!!
    private lateinit var pagerAdapter: PhotoPaging3Adapter
    private lateinit var viewModel: PopularViewModel
    private lateinit var viewModelFactory: PopularViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModelFactory = PopularViewModelFactory("popular")
        viewModel = ViewModelProvider(this,viewModelFactory)[PopularViewModel::class.java]
        pagerAdapter = PhotoPaging3Adapter()
        launch {
            viewModel.flow.collect {
                pagerAdapter.submitData(it)
            }
        }
        binding.rv.adapter = pagerAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}