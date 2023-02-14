package fragment

import Apdapter.adapter_load_fragment_playmusic
import android.annotation.SuppressLint
import android.app.Dialog
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.appmusiconline.MainActivity
import com.example.appmusiconline.R
import com.example.appmusiconline.databinding.BottomSheetDiglogPlayMusicBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import me.relex.circleindicator.CircleIndicator3
import java.text.SimpleDateFormat

class Bottom_sheet_dialog_playmusic :BottomSheetDialogFragment()
{
    private lateinit var vp_load_fragment_play_music:ViewPager2
    private lateinit var indicator: CircleIndicator3
    private lateinit var mainActivity: MainActivity
    private lateinit var btn_play:ImageButton
    private lateinit var btn_pause:ImageButton
    private lateinit var btn_next:ImageButton
    private lateinit var btn_prious:ImageButton
    private lateinit var timestart:SimpleDateFormat
    private lateinit var seekBar: SeekBar
    private lateinit var timeend:SimpleDateFormat
    private lateinit var tv_time_end:TextView
    private lateinit var tv_time_start:TextView
    private lateinit var btn_finish_bottom_sheet_play_music:ImageButton
    private lateinit var adapter_vp_load_fragment_playmusci :adapter_load_fragment_playmusic
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottom_sheet_dialog_playmusic =  super.onCreateDialog(savedInstanceState)
        val viewdialog:View=LayoutInflater.from(context).inflate(R.layout.bottom_sheet_diglog_play_music,null)
        bottom_sheet_dialog_playmusic.setContentView(viewdialog)
        Mapping(viewdialog)
        init()
        SetdataUi()
        SetSequence(mainActivity.positionsong)
        SetOnClick()
        val behavior=BottomSheetBehavior.from(viewdialog.parent as View)
        behavior.state=BottomSheetBehavior.STATE_EXPANDED

        return bottom_sheet_dialog_playmusic
    }

    private fun SetSequence( a:Int) {
        for (i in 0 until mainActivity.listmusic.size )
        {

            if(a==i) {
                setdatasong(mainActivity.listmusic.get(i).path)
                set_time_end()
                UpdateTimeStart()



            }
        }

    }

    private fun SetdataUi() {
        vp_load_fragment_play_music.adapter=adapter_vp_load_fragment_playmusci
        vp_load_fragment_play_music.currentItem=1
        indicator.setViewPager(vp_load_fragment_play_music)
    }

    private fun SetOnClick() {
       btn_finish_bottom_sheet_play_music.setOnClickListener(
           {
               mainActivity.DissmissBottomSheetPlayMusic()
           }
       )
        btn_pause.setOnClickListener(
            {
                mainActivity.mediaPlayer.pause()
                btn_play.visibility=View.VISIBLE
                btn_pause.visibility=View.INVISIBLE
            }
        )
        btn_play.setOnClickListener(
            {
                mainActivity.mediaPlayer.start()
                btn_play.visibility=View.INVISIBLE
                btn_pause.visibility=View.VISIBLE
            }
        )
        btn_next.setOnClickListener(
            {
                mainActivity.positionsong=mainActivity.positionsong+1
                setdatasong(mainActivity.music.path)

            }
        )
        seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener
        {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
               if(p2)
               {
                   mainActivity.mediaPlayer.seekTo(p1)
               }
            }

            override fun onStartTrackingTouch(seebar: SeekBar?) {
                if(seebar!=null)
                {
                    mainActivity.mediaPlayer.seekTo(seekBar.progress)
                }
            }

            override fun onStopTrackingTouch(seebar: SeekBar?) {
                 if(seebar!=null)
                 {
                     mainActivity.mediaPlayer.seekTo(seekBar.progress)
                 }
            }

        })
    }

    private fun init() {
         timestart=SimpleDateFormat("mm:ss")
         timeend= SimpleDateFormat("mm:ss")
        mainActivity=requireActivity() as MainActivity
        adapter_vp_load_fragment_playmusci= adapter_load_fragment_playmusic(requireActivity())



    }
    private fun setdatasong(path:String)
    {
        mainActivity.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mainActivity.mediaPlayer.setDataSource(path)
        mainActivity.mediaPlayer.prepare()
        mainActivity.mediaPlayer.start()
    }

    private fun Mapping(view:View) {
        vp_load_fragment_play_music=view.findViewById(R.id.vp_load_fragment_playmusic)
        indicator=view.findViewById(R.id.indicator)
        btn_play=view.findViewById(R.id.btn_play)
        btn_pause=view.findViewById(R.id.btn_pause)
        btn_next=view.findViewById(R.id.btn_next)
        btn_prious=view.findViewById(R.id.btn_prious)
        seekBar=view.findViewById(R.id.seekbar)
        tv_time_end=view.findViewById(R.id.tv_time_end)
        tv_time_start=view.findViewById(R.id.tv_time_start)

        btn_finish_bottom_sheet_play_music=view.findViewById(R.id.btn_finish_bottom_sheet_play_music)
    }
    private fun set_time_end()
    {
        tv_time_end.text=timeend.format(mainActivity.mediaPlayer.duration)
        seekBar.progress=0
        seekBar.max=mainActivity.mediaPlayer.duration

    }
    private fun UpdateTimeStart()
    {
      val handler :Handler=Handler()
          handler.postDelayed(@SuppressLint("SuspiciousIndentation")
          object :Runnable
          {
              override fun run() {

                tv_time_start.text=timestart.format(mainActivity.mediaPlayer.currentPosition)
                  seekBar.progress=mainActivity.mediaPlayer.currentPosition
                handler.postDelayed(this,1000)
              }

          },100)
    }


}