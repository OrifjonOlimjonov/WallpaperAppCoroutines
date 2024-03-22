package uz.orifjon.wallpaperappcoroutines.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import uz.orifjon.wallpaperappcoroutines.R
import uz.orifjon.wallpaperappcoroutines.adapters.PhotoPaging3Adapter
import uz.orifjon.wallpaperappcoroutines.databinding.FragmentRandomBinding
import uz.orifjon.wallpaperappcoroutines.models.database.Photo
import kotlin.coroutines.CoroutineContext

class RandomFragment : Fragment(), CoroutineScope {

    private var _binding: FragmentRandomBinding? = null


    private val binding get() = _binding!!
    private lateinit var list: ArrayList<Photo>
    private lateinit var pagerAdapter: PhotoPaging3Adapter
    private val TAG = "RandomFragment"
    private lateinit var job:Job
    private lateinit var viewModel: RandomViewModel
    private lateinit var viewModelFactory: RandomViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        val root: View = binding.root
        job = Job()

//        list = ArrayList()
        // adapter = AdapterRecyclerView(list)
//        ApiClient.getRetrofit().create(ApiService::class.java).getList("1", "18", "nature")
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
//                onNext = {
//                    list = it.photos as ArrayList<Photo>
////                    adapter = AdapterRecyclerView(list)
////                    binding.rv.adapter = adapter
//                }
//            )
        viewModelFactory = RandomViewModelFactory("nature")
        viewModel = ViewModelProvider(this,viewModelFactory)[RandomViewModel::class.java]

        launch {
            viewModel.flow.collect {
                pagerAdapter.submitData(it)
            }
        }
        pagerAdapter = PhotoPaging3Adapter(){photo, i ->
            val bundle = Bundle()
            bundle.putParcelable("photo",photo)
            findNavController().navigate(R.id.viewPhotoFragment,bundle)
        }

        binding.rv.adapter = pagerAdapter


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
