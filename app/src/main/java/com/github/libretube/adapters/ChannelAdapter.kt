package com.github.libretube.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.github.libretube.MainActivity
import com.squareup.picasso.Picasso
import com.github.libretube.PlayerFragment
import com.github.libretube.R
import com.github.libretube.obj.StreamItem
import com.github.libretube.videoViews

class ChannelAdapter(private val videoFeed: MutableList<StreamItem>): RecyclerView.Adapter<ChannelViewHolder>() {
    override fun getItemCount(): Int {
        return videoFeed.size
    }
    fun updateItems(newItems: List<StreamItem>){
        videoFeed.addAll(newItems)
        println("suck another dick: "+newItems[0].title)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.video_channel_row,parent,false)
        return ChannelViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val trending = videoFeed[position]
        holder.v.findViewById<TextView>(R.id.channel_description).text = trending.title
        holder.v.findViewById<TextView>(R.id.channel_views).text = trending.views.videoViews()+" • "+trending.uploadedDate
        val thumbnailImage = holder.v.findViewById<ImageView>(R.id.channel_thumbnail)
        Picasso.get().load(trending.thumbnail).into(thumbnailImage)
        holder.v.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("videoId",trending.url!!.replace("/watch?v=",""))
            var frag = PlayerFragment()
            frag.arguments = bundle
            val activity = holder.v.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                .remove(PlayerFragment())
                .commit()
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commitNow()
        }
    }
}
class ChannelViewHolder(val v: View): RecyclerView.ViewHolder(v){
    init {
    }
}