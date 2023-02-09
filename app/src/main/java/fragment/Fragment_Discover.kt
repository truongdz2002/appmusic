package fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appmusiconline.R



class Fragment_Discover :Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    var view:View
        view= inflater.inflate(R.layout.fragment__discover, container, false)
        return view
    }



    }
