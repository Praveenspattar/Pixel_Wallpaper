package com.myapps.wallapers_app.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapps.pixel_wallpapers_app.databinding.TopItemWallpaperBinding
import com.myapps.wallapers_app.MainActivity2
import com.myapps.wallapers_app.Models.TopPhotos

class WallTopAdapter : RecyclerView.Adapter<WallTopAdapter.WallpaperViewHolder>() {

    var list = ArrayList<TopPhotos>()
    lateinit var contextx : Context

    fun setTopWalpaperData(list : ArrayList<TopPhotos>,context: Context){
        this.list = list
        this.contextx = context

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        return WallpaperViewHolder(TopItemWallpaperBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        Glide.with(holder.itemView).load(list[position].topPhotoResourceId).into(holder.binding.imageView2)
//        val currentItem = list[position]
//        holder.binding.imageView2.setImageResource(currentItem.topPhotoResourceId)

        holder.itemView.setOnClickListener({
            val intent = Intent(contextx, MainActivity2::class.java)
            intent.putExtra("name",list[position].searchString)
            holder.itemView.context.startActivity(intent)
        })

    }

    class WallpaperViewHolder(val binding : TopItemWallpaperBinding) : RecyclerView.ViewHolder(binding.root){
    }
}