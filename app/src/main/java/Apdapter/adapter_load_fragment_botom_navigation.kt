package Apdapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import fragment.Fragment_Discover
import fragment.Fragment_Fllowing
import fragment.Fragment_Individual
import fragment.Fragment_Radio

class adapter_load_fragment_botom_navigation(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount()=4

    override fun createFragment(position: Int): Fragment {
      return when(position)
      {
          0->{Fragment_Individual()}
          1->{Fragment_Discover()}
          2->{Fragment_Radio()}
          3->{Fragment_Fllowing()}

          else -> {throw Resources.NotFoundException("Không có vị trí này")}
      }
    }
}