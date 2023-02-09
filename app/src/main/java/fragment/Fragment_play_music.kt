package fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import com.example.appmusiconline.MainActivity
import com.example.appmusiconline.R
import com.example.appmusiconline.databinding.FragmentPlayMusicBinding


class Fragment_play_music : Fragment() {

private lateinit var binding:FragmentPlayMusicBinding
private lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentPlayMusicBinding.inflate(inflater,container,false)
        Init()
        SetdataUi()
        Runimg()
        return binding.root

    }

    private fun Runimg() {
        val runnable=object :Runnable
        {
            override fun run() {
                binding.image.animate().rotation(2000F).withEndAction(this).setDuration(100000)
                    .setInterpolator(LinearInterpolator()).start()
            }

        }
        binding.image.animate().rotation(2000F).withEndAction(runnable).setDuration(100000)
            .setInterpolator(LinearInterpolator()).start()

    }

    private fun Init() {
        mainActivity=requireActivity() as MainActivity
    }

    private fun SetdataUi() {
        binding.tvNameSongInFragmentPlayMucsic.text=mainActivity.music.Tiltle
        binding.tvNameArtistInFragmentPlayMusic.text=mainActivity.music.Artist
    }


}