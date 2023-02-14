package fragment

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appmusiconline.databinding.FragmentSongsOnDeviceBinding
import Apdapter.adapter_music
import Object.Music
import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.MediaStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmusiconline.MainActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.text.FieldPosition


class Fragment_Songs_On_Device : Fragment() {
    private lateinit var binding:FragmentSongsOnDeviceBinding
    private lateinit var adapterMusic:adapter_music
    lateinit var mainActivity: MainActivity
    @SuppressLint("RestrictedApi")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSongsOnDeviceBinding.inflate(inflater,container,false)
        Init()
        runtimepermission()
        SetdataUi()
        onClick()
        return binding.root
    }

    private fun SetdataUi() {
        binding.rcvMusic.layoutManager = LinearLayoutManager(requireActivity())
        binding.rcvMusic.setHasFixedSize(true)
        binding.rcvMusic.adapter = adapterMusic

    }
    private fun onClick() {
        binding.btnBackFragmentSongsOnDevice.setOnClickListener(
            {
                mainActivity.ReplaceFragment(Fragment_Individual())
            }
        )
        adapterMusic.SetClickItem(object :adapter_music.ItemClick
        {
            override fun ClickItemSong(music: Music,position: Int) {
                mainActivity.showBotomSheetPlayMusic()
                mainActivity.music=music
                mainActivity.positionsong=position
                mainActivity.mediaPlayer.reset()
            }

        })
    }

    public fun runtimepermission()
    {

        Dexter.withContext(context)
            .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener
            {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    let {
                        if(report!!.areAllPermissionsGranted())
                        {

                            mainActivity.listmusic=GetAllAudio()
                            adapterMusic.Setdata(mainActivity.listmusic)
                            binding.collap.title="Bài hát("+GetAllAudio().size.toString()+")"

                        }

                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }

            }).check()




    }

    private fun Init() {

        mainActivity=requireActivity()as MainActivity
        adapterMusic= adapter_music(requireActivity())


    }


    @SuppressLint("Recycle", "Range", "SuspiciousIndentation")

    private  fun GetAllAudio():ArrayList<Music> {
        val tempList = ArrayList<Music>()
        val selection= MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA
        )
        val cursor = requireActivity().contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null,
            MediaStore.Audio.Media.DATE_ADDED, null
        )
        if (cursor != null)
        {


            if (cursor.moveToFirst())
                do {
                    val titleC =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val idC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val albumC =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                    val artistC =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val pathC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val durationC =
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val music = Music(
                        Id = idC,
                        Tiltle = titleC,
                        album = albumC,
                        Artist = artistC,
                        Duration = durationC,
                        path = pathC
                    )
                    val file = File(music.path)
                    if (file.exists())

                        tempList.add(music)
                } while (cursor.moveToNext())
            cursor.close()
        }
        return tempList
    }


}