package uz.orifjon.wallpaperappcoroutines.ui.liked

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.orifjon.wallpaperappcoroutines.R
import uz.orifjon.wallpaperappcoroutines.adapters.AdapterRecyclerView
import uz.orifjon.wallpaperappcoroutines.adapters.RecyclerViewAdapterLiked
import uz.orifjon.wallpaperappcoroutines.databinding.FragmentLikedBinding
import uz.orifjon.wallpaperappcoroutines.models.database.PhotoDatabase

class LikedFragment : Fragment() {

    private lateinit var binding: FragmentLikedBinding
    private lateinit var adapter: RecyclerViewAdapterLiked
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLikedBinding.inflate(inflater, container, false)
        adapter = RecyclerViewAdapterLiked(requireContext()) { photo, index ->
            val bundle = Bundle()
            bundle.putParcelable("photo", photo)
            findNavController().navigate(R.id.viewPhotoFragment, bundle)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvLiked.adapter = adapter
        val listLikedPhoto = PhotoDatabase.getDatabase(requireContext()).photoDao().list()
        adapter.submitList(listLikedPhoto)


    }


}