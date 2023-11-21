package com.myapps.wallapers_app

import android.Manifest
import android.app.WallpaperManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.myapps.pixel_wallpapers_app.databinding.ActivityDisplayWallpaperBinding
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class DisplayWallpaper : AppCompatActivity() {

    lateinit var binding : ActivityDisplayWallpaperBinding
    lateinit var bitmap: Bitmap
    lateinit var bitmapDrawable: BitmapDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  ActivityDisplayWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.getStringExtra("image")

        Glide.with(this).load(intent).into(binding.imageView)

        binding.setBtn.setOnClickListener(View.OnClickListener { setBackground() })

        binding.downloadBtn.setOnClickListener({
            // Check if the WRITE_EXTERNAL_STORAGE permission is granted
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {

                // Request the permission
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            } else {
                // Permission is already granted, proceed with your code
                setupDownloadButton()
            }
        })

    }

    private fun setupDownloadButton() {
//        binding.downloadBtn.setOnClickListener(View.OnClickListener {
            bitmapDrawable = binding.imageView.getDrawable() as BitmapDrawable
            bitmap = bitmapDrawable.getBitmap()
            val fileOutputStream: FileOutputStream
            val sdCard = Environment.getExternalStorageDirectory()
            val Directory = File(sdCard.absolutePath, "/Downloads")
            Directory.mkdir()
            val filename = String.format("%d.jpg", System.currentTimeMillis())
            val outfile = File(Directory, filename)
            try {
                fileOutputStream = FileOutputStream(outfile)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                fileOutputStream.flush()
                fileOutputStream.close()
                val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                intent.data = Uri.fromFile(outfile)
                sendBroadcast(intent)
                Toast.makeText(
                    this@DisplayWallpaper,
                    "Image saved Successfully",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: RuntimeException) {
                throw RuntimeException(e)
            } catch (e: FileNotFoundException) {
                throw RuntimeException(e)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
//        })
    }


    /*private void downloadImage(String img) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            //permission not granted , request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }else {
            startImagedownload(img);
        }
    }

    private void startImagedownload(String img) {
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("image");
        String request = new DownloadManager.Request(uri).toString();

        //set destination and file name

    }*/


    /*private void downloadImage(String img) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            //permission not granted , request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }else {
            startImagedownload(img);
        }
    }

    private void startImagedownload(String img) {
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("image");
        String request = new DownloadManager.Request(uri).toString();

        //set destination and file name

    }*/
    private fun setBackground() {
        val bitmap = (binding.imageView.getDrawable() as BitmapDrawable).bitmap
        val manager = WallpaperManager.getInstance(applicationContext)
        try {
            manager.setBitmap(bitmap)
            Toast.makeText(this, "Wallpaper Updated", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Error" + e.message, Toast.LENGTH_SHORT).show()
            throw RuntimeException(e)
        }
    }
}

