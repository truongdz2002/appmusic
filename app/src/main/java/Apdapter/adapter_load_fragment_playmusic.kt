package Apdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import fragment.Fragment_lyrics
import fragment.Fragment_play_music
import fragment.Fragment_songs_infor

class adapter_load_fragment_playmusic(fragment:FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3;

    }

    override fun createFragment(position: Int): Fragment {
        return when(position)
        {
            0->{Fragment_songs_infor()}
            1->{Fragment_play_music()}
            2->{Fragment_lyrics()}
            else -> {Fragment_play_music()}
        }

        }

    }
