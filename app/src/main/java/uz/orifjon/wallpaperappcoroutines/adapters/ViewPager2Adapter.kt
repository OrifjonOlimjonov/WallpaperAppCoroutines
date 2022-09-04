package uz.orifjon.wallpaperappcoroutines.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.orifjon.wallpaperappcoroutines.ui.viewpager.ViewPagerFragment

class ViewPager2Adapter(lifecycle: Lifecycle,fragment: FragmentManager,var list:ArrayList<String>):FragmentStateAdapter(fragment,lifecycle) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
                return ViewPagerFragment.newInstance(list[position],list[position])
    }


}