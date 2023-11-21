package com.myapps.wallapers_app.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapps.pixel_wallpapers_app.databinding.ItemWallpaperBinding
import com.myapps.wallapers_app.DisplayWallpaper
import com.myapps.wallapers_app.MainActivity2
import com.myapps.wallapers_app.Models.Photo

class WallAdapter2 : RecyclerView.Adapter<WallAdapter2.WallViewHolder2>() {

    var arrayList = ArrayList<Photo>()
    lateinit var aContext: Context

    fun setData2( arrayList: ArrayList<Photo>,context: Context){
        this.arrayList = arrayList
        this.aContext = context

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallViewHolder2 {
        return WallViewHolder2(ItemWallpaperBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: WallViewHolder2, position: Int) {
        Glide.with(holder.itemView).load(arrayList[position].src.portrait).into(holder.binding.imageView2)
        holder.itemView.setOnClickListener( {
            val intent = Intent(aContext,DisplayWallpaper::class.java)
            intent.putExtra("image",arrayList[position].src.portrait)
            holder.itemView.context.startActivity(intent)
        })
    }

    class WallViewHolder2(val binding : ItemWallpaperBinding) : RecyclerView.ViewHolder(binding.root){}

}