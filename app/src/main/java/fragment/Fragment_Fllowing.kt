package fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appmusiconline.R


class Fragment_Fllowing : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__fllowing, container, false)
    }



}