package uz.orifjon.wallpaperappcoroutines.ui.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.orifjon.wallpaperappcoroutines.R
import uz.orifjon.wallpaperappcoroutines.adapters.PhotoPaging3Adapter
import uz.orifjon.wallpaperappcoroutines.databinding.FragmentViewPagerBinding
import kotlin.coroutines.CoroutineContext

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ViewPagerFragment : Fragment() ,CoroutineScope{

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var viewPagerViewModel: ViewPagerViewModel
    private lateinit var viewPagerViewModelFactory: ViewPagerViewModelFactory
    private lateinit var pagerAdapter: PhotoPaging3Adapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        viewPagerViewModelFactory = ViewPagerViewModelFactory(param1!!)
        viewPagerViewModel = ViewModelProvider(this,viewPagerViewModelFactory)[ViewPagerViewModel::class.java]

        pagerAdapter = PhotoPaging3Adapter(){photo,i->
            val bundle = Bundle()
            bundle.putParcelable("photo",photo)
            findNavController().navigate(R.id.viewPhotoFragment,bundle)
        }
        binding.rv.adapter = pagerAdapter

        launch {
            viewPagerViewModel.flow.collect{
                pagerAdapter.submitData(it)
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}