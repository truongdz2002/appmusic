package fragment

import Apdapter.adapter_library
import Apdapter.adapter_library.ItemClick
import Object.Library
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmusiconline.MainActivity
import com.example.appmusiconline.R
import com.example.appmusiconline.databinding.FragmentIndividualBinding


class Fragment_Individual : Fragment() {


lateinit var listLibrary:ArrayList<Library>
lateinit var adapterLibrary:adapter_library
lateinit var mainActivity:MainActivity
private lateinit var binding:FragmentIndividualBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
         binding=FragmentIndividualBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        DataList()
        Mapping()
        Init()
        return binding.root
    }
    private fun DataList() {
        listLibrary= arrayListOf<Library>()
        listLibrary.add(Library(R.drawable.icon_love,"Bài hát"))
        listLibrary.add(Library(R.drawable.ic_baseline_arrow_circle_down_24,"Tải xuống"))
        listLibrary.add(Library(R.drawable.ic_baseline_mic_external_on_24,"Karaoke"))
        listLibrary.add(Library(R.drawable.icon_love,"Nghệ sĩ"))
        listLibrary.add(Library(R.drawable.ic_baseline_ondemand_video_24,"MV"))
        listLibrary.add(Library(R.drawable.ic_baseline_upload_24,"Up load"))
    }

    private fun Init() {
        mainActivity=requireActivity() as MainActivity
        binding.rcvLibrary.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rcvLibrary.layoutManager=GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false)
        adapterLibrary=adapter_library(listLibrary,requireActivity())
        adapterLibrary.SetOnClickItem(object : adapter_library.ItemClick
        {
            override fun itemclick(library: Library) {

               mainActivity.ReplaceFragment(Fragment_Songs_On_Device())
            }

        })
        binding.rcvLibrary.setHasFixedSize(true)
        binding.rcvLibrary.adapter=adapterLibrary
    }
    private fun Mapping() {


    }



}