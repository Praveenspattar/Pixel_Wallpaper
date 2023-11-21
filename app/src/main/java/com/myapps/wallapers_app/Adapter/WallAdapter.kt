package com.myapps.wallapers_app.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapps.pixel_wallpapers_app.databinding.ItemWallpaperBinding
import com.myapps.wallapers_app.DisplayWallpaper

class WallAdapter : RecyclerView.Adapter<WallAdapter.WallpaperViewHolder>() {

    var list = ArrayList<com.myapps.wallapers_app.Models.Photo>()
    lateinit var contextx : Context

    fun setWalpaperData(list: ArrayList<com.myapps.wallapers_app.Models.Photo>, context : Context){

        this.list = list
        this.contextx = context

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {

//        return WallpaperViewHolder(ItemWallpaperBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        return WallpaperViewHolder(ItemWallpaperBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        Glide.with(holder.itemView).load(list[position].src.portrait).into(holder.binding.imageView2)

        holder.itemView.setOnClickListener({
            val intent = Intent(contextx,DisplayWallpaper::class.java)
            intent.putExtra("image",list[position].src.portrait)
            holder.itemView.context.startActivity(intent)
        })
    }

    class WallpaperViewHolder(val binding : ItemWallpaperBinding) : RecyclerView.ViewHolder(binding.root){
    }


}