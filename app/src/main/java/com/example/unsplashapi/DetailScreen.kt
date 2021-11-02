package com.example.unsplashapi

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.unsplashapi.adapter.ImageAdapter

class PlantPopup(private val adapter: ImageAdapter, private val photo: MyDataItem, private val popupType: Int) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        if(popupType == 1){
            //findViewById<ShapeableImageView>(R.id.image_item_story).strokeColor
            setContentView(R.layout.detailscreen)
            setupComponents()
            setupCloseButton()
        }
        if(popupType == 2){
            setContentView(R.layout.picturefullscreen)
            setupComponentsImage()
            setupCloseButton()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener{


            dismiss()


        }
    }

    private fun setupComponents() {
        //actualiser l'image de la plante
        val plantImage=findViewById<ImageView>(R.id.image_popup)
        val storyImage=findViewById<ImageView>(R.id.image_item_story)

        Glide.with(adapter.context).load(Uri.parse(photo.urls.regular)).into(plantImage)
        Glide.with(adapter.context).load(Uri.parse(photo.user.profile_image.small)).into(storyImage)

        findViewById<TextView>(R.id.name_item)?.text= photo.user.first_name //+ " " + currentPlant.user.last_name

        findViewById<TextView>(R.id.popup_smallDescription).text=photo.description
        findViewById<TextView>(R.id.popup_longDescription).text=photo.alt_description

        findViewById<TextView>(R.id.popup_createdAt).text=photo.updated_at.substring(0, 10)
        findViewById<TextView>(R.id.popup_updatedAt).text=photo.updated_at.substring(0, 10)



    }
    private fun setupComponentsImage() {
        //actualiser l'image de la plante
        val plantImage=findViewById<ImageView>(R.id.storyImage)
       // val  cardviewtest =findViewById<CardView>(R.id.cardViewParent)
        Glide.with(adapter.context).load(Uri.parse(photo.urls.small)).into(plantImage)
        //cardviewtest.setCardBackgroundColor(R.color.lightGray)

//        val test =findViewById<ShapeableImageView>(R.id.image_item_story)
//        val test2=test.shapeAppearanceModel.toBuilder().setAllCorners(CornerFamily.ROUNDED, 125f).build()
//        test.shapeAppearanceModel=test2
//        setContentView(R.layout.activity_main)

        //findViewById<ShapeableImageView>(R.id.image_item_story).setStroke(3, Color.RED)

    }


}