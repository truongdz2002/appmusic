package Apdapter

import Object.Music
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmusiconline.R

class adapter_music(var context: Context) :
    RecyclerView.Adapter<adapter_music.adapter_music_viewholder>() {
  private lateinit var itemview:View
  private lateinit var itemClick: ItemClick
  private  lateinit var list: ArrayList<Music>
  public interface ItemClick{
      fun ClickItemSong(music: Music,position: Int)
  }
    public fun SetClickItem(m_item:ItemClick)
    {
        itemClick=m_item
    }
    public fun Setdata(list: ArrayList<Music>)
    {
        this.list=list
        notifyDataSetChanged()
    }

   public  class adapter_music_viewholder(itemView: View) : RecyclerView.ViewHolder(itemView)
   {
       var tv_name_music:TextView=itemView.findViewById(R.id.tv_name_music)
       var tv_name_artist:TextView=itemView.findViewById(R.id.tv_name_artist)
       var img_music:ImageView=itemView.findViewById(R.id.img_music)
   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter_music_viewholder {
        itemview=LayoutInflater.from(parent.context).inflate(R.layout.item_music,parent,false)
        return adapter_music_viewholder(itemview)
    }

    override fun onBindViewHolder(holder: adapter_music_viewholder, position: Int) {
       var current : Music=list[position]
        holder.tv_name_music.text=current.Tiltle
        holder.tv_name_artist.text=current.Artist
        holder.img_music.setOnClickListener(
            {
                itemClick.ClickItemSong(current,position)
            }
        )
        holder.tv_name_music.setOnClickListener({
            itemClick.ClickItemSong(current,position)
        })
        holder.tv_name_artist.setOnClickListener(
            {
                itemClick.ClickItemSong(current,position)
            }
        )
    }

    override fun getItemCount(): Int {
        if(list!=null)
        {
            return list.size
        }
        return 0
    }

}