package uz.orifjon.wallpaperappcoroutines.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import uz.orifjon.wallpaperappcoroutines.R
import uz.orifjon.wallpaperappcoroutines.adapters.PhotoPaging3Adapter
import uz.orifjon.wallpaperappcoroutines.adapters.ViewPager2Adapter
import uz.orifjon.wallpaperappcoroutines.databinding.ActivityMainBinding
import uz.orifjon.wallpaperappcoroutines.databinding.FragmentHomeBinding
import uz.orifjon.wallpaperappcoroutines.databinding.ItemTabBinding
import kotlin.coroutines.CoroutineContext

class HomeFragment : Fragment(), CoroutineScope {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var pagerAdapter: PhotoPaging3Adapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory
    private lateinit var viewPager2Adapter: ViewPager2Adapter
    private lateinit var job: Job
    private val TAG = "HomeFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModelFactory = HomeViewModelFactory("water")
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        pagerAdapter = PhotoPaging3Adapter(){photo, i ->
            val bundle = Bundle()
            bundle.putParcelable("photo",photo)
            findNavController().navigate(R.id.viewPhotoFragment,bundle)
        }
        val list = arrayListOf("All", "News", "Nature", "Technology", "Animals")
        viewPager2Adapter = ViewPager2Adapter(lifecycle, childFragmentManager, list)
        binding.viewPager2.adapter = viewPager2Adapter
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager2
        ) { tab: TabLayout.Tab, position: Int ->
            val bindingItem: ItemTabBinding =
                ItemTabBinding.inflate(LayoutInflater.from(context), null, false)
            bindingItem.tabTitle.text = list[position]
            bindingItem.tabImage.setImageResource(R.drawable.indicator)
            if (position == 0) {
                bindingItem.tabTitle.setTextColor(Color.WHITE)
            } else {
                bindingItem.tabTitle.setTextColor(Color.parseColor("#8A8A8A"))
                bindingItem.tabImage.visibility = View.INVISIBLE
            }
            tab.customView = bindingItem.root
            binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val binding: ItemTabBinding = ItemTabBinding.bind(tab.customView!!)
                    binding.tabTitle.setTextColor(Color.WHITE)
                    binding.tabImage.visibility = View.VISIBLE
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    val binding: ItemTabBinding = tab.customView?.let { ItemTabBinding.bind(it) }!!
                    binding.tabTitle.setTextColor(Color.parseColor("#8A8A8A"))
                    binding.tabImage.visibility = View.INVISIBLE
                }

                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        }.attach()
        job = Job()
        launch {
            viewModel.flow.collect {
                pagerAdapter.submitData(it)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}