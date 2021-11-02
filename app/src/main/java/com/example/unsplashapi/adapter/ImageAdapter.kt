package com.example.unsplashapi.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashapi.MainActivity
import com.example.unsplashapi.MyDataItem
import com.example.unsplashapi.PlantPopup
import com.example.unsplashapi.R
import kotlin.coroutines.coroutineContext


 class ImageAdapter(var context : MainActivity,  private var photoList: ArrayList<MyDataItem>, private val layoutId: Int) :RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //recupérer l'image
        val userprofile: ImageView = itemView.findViewById(R.id.Imageuserprofile)
        val image: ImageView = itemView.findViewById(R.id.imageVerticalItem)
        val user: TextView = itemView.findViewById(R.id.username)
        val created_at: TextView = itemView.findViewById(R.id.created_at)
        }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                //recuperer les information
                // de la plante
                val photo = photoList[position]

                //utiliser glide pour recuperer l'image à partir de son lien -> composant
                holder.image?.let {
                    Glide.with(context).load(Uri.parse(photo.urls.regular)).into(
                        it
                    )
                }
                holder.userprofile?.let {
                    Glide.with(context).load(Uri.parse(photo.user.profile_image.medium)).into(
                        it
                    )
                }

                //mettre à jour le nom de la plante

                holder.user?.text= photo.user.first_name //+ " " + currentPlant.user.last_name
                //holder.textStory?.text=currentPlant.user.name

                //mettre à jour la description de la plante
                holder.created_at?.text=photo.created_at.substring(0, 10)

                //interaction lors du clic sur une plante
                holder.itemView.setOnClickListener {
                    //afficher la popup
                    if(layoutId == R.layout.storyrow){

                        PlantPopup(this, photo, 2).show()
                    }
                    if(layoutId == R.layout.image_vertical_row){
                        PlantPopup(this, photo, 1).show()
                    }

                }
            }



     fun replaceDataSet(list: ArrayList<MyDataItem>) {
         photoList = list
         notifyDataSetChanged()
     }
    override fun getItemCount():Int = photoList.size
}






