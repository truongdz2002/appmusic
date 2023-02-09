package Apdapter

import Object.Library
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmusiconline.R

class adapter_library(private var listLibrary:ArrayList<Library>,var context: Context):
    RecyclerView.Adapter<adapter_library.adapter_library_viewholder>() {
    private lateinit var itemClick: ItemClick
 public interface ItemClick
 {
     fun itemclick(library: Library)
 }
    public fun SetOnClickItem(m_itemClick: ItemClick)
    {
       itemClick=m_itemClick
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter_library_viewholder {
       val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_library,parent,false)
        return adapter_library_viewholder(itemView)
    }

    override fun onBindViewHolder(holder: adapter_library_viewholder, position: Int) {
       val CurrentItemLibrary:Library=listLibrary[position]
        holder.ImgLibrary.setImageResource(CurrentItemLibrary.imgLibrary)
        holder.titleLibrary.text=CurrentItemLibrary.TitleLibrary
        when(position)
        {
            1->holder.ImgLibrary.setOnClickListener(
                {
                    itemClick.itemclick(CurrentItemLibrary)
                }
            )


        }
    }

    override fun getItemCount(): Int {
       return listLibrary.size
    }
    class adapter_library_viewholder(itemView:View): RecyclerView.ViewHolder(itemView)
    {
      val ImgLibrary:ImageView=itemView.findViewById(R.id.img_icon_libary)
        var titleLibrary:TextView=itemView.findViewById(R.id.tv_item_library)
    }
}
