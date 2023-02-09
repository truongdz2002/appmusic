package fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appmusiconline.R
import com.example.appmusiconline.databinding.FragmentSongsInforBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class Fragment_songs_infor : Fragment() {
    private lateinit var binding: FragmentSongsInforBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSongsInforBinding.inflate(inflater,container,false)
        return binding.root
    }


}