package com.example.appmusiconline

import Apdapter.adapter_load_fragment_botom_navigation
import Object.Music
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.appmusiconline.databinding.BottomSheetDiglogPlayMusicBinding
import com.google.android.material.bottomappbar.BottomAppBar.Behavior
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import fragment.*
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private lateinit var vp_load_fragment_botom_navigation: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    public lateinit  var listmusic:ArrayList<Music>
    public lateinit var music: Music
    public lateinit var mediaPlayer: MediaPlayer
    public var positionsong:Int = 0

    public val botomsheetdialogplaymusic=Bottom_sheet_dialog_playmusic()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        Mapping()
        Init()
        ReplaceFragment(Fragment_Individual())
        SetcheckedItemBottomNavigation()
    }

    public fun ReplaceFragment(fragment: Fragment) {
        val transaction =supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_fragment,fragment)
        transaction.commit()
    }
    public fun CheckPositionsong() {
        for( i in 0 until listmusic.size)
        {
            if(i==positionsong)
            {
                music=listmusic.get(i)
            }

        }
    }
    public fun Init() {
        listmusic= arrayListOf<Music>()
        mediaPlayer=MediaPlayer()

    }
    public fun showBotomSheet()
    {
        botomsheetdialogplaymusic.show(supportFragmentManager,"ok")
    }
    public fun DissmissBottomSheet()
    {
        botomsheetdialogplaymusic.dismiss()
    }

    private fun Mapping()
    {
        bottomNavigationView=findViewById(R.id.bottom_navigation)

    }
    private  fun SetcheckedItemBottomNavigation()
    {
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.btn_discover->ReplaceFragment(Fragment_Discover())
                R.id.btn_individual->ReplaceFragment(Fragment_Individual())
                R.id.btn_radio->ReplaceFragment(Fragment_Radio())
                R.id.btn_fllowing->ReplaceFragment(Fragment_Fllowing())
                else->{}


            }
              true
        }
    }
}




