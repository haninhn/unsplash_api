package com.example.unsplashapi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashapi.adapter.ImageAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
const val BASE_URL ="https://api.unsplash.com/"
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewVertical: RecyclerView
    private lateinit var recyclerViewHorizontal: RecyclerView
    val plantlist = arrayListOf<PlantModel>()
    var list = arrayListOf<MyDataItem>()
    var adapter = ImageAdapter(this, list, R.layout.storyrow)
    var adapter2 = ImageAdapter(this, list, R.layout.picturefullscreen)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //story
        recyclerViewHorizontal= findViewById(R.id.Story)
        recyclerViewHorizontal.adapter = adapter
        //listImage
        recyclerViewVertical = findViewById(R.id.images)
        recyclerViewVertical.adapter = adapter2
        recyclerViewVertical.layoutManager = GridLayoutManager(applicationContext,2)



        getMyData()


    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface:: class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>) {
                list = (response.body() as ArrayList<MyDataItem>?)!!
                if (list != null){
                    adapter.replaceDataSet(list)
                    adapter2.replaceDataSet(list)
                  //  progress.visibility = View.GONE
            }
           }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                 d("mainActivity", "onFailure" + t.message)
            }
        })
    }

}

