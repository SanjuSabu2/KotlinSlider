package com.example.kotlinslider

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.kotlinslider.adapter.Adapter
import com.example.kotlinslider.api.CartApi
import com.example.kotlinslider.api.ServiceBuilder
import com.example.kotlinslider.model.MainModel
import com.example.kotlinslider.model.ResponseArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : Activity() {
    lateinit var pager : ViewPager
    lateinit var images : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context: Context
        var timer: Timer?
        var myImageData: MainModel
        val responseArrayData : ArrayList<ResponseArray> = ArrayList()
        val request = ServiceBuilder.buildService(CartApi::class.java)
        val call = request.getImages()
        context = this
        call.enqueue(object : Callback<MainModel> {
            override fun onResponse(call: Call<MainModel>, response: Response<MainModel>) {
                myImageData = response.body()!!
                if (myImageData.status == 100) {
                    responseArrayData.add(response.body()!!.responseArray)
                    images = responseArrayData[0].banner_images
//                    pager = findViewById<ViewPager>(R.id.pager) as ViewPager
                    pager = findViewById(R.id.pager)
                    var f = 0
                    var d = 0
                    val adapter = Adapter(images, context)
                    pager.adapter = adapter
                    val timerTask: TimerTask = object : TimerTask() {
                        override fun run() {
//                            pager.post(Runnable { pager.currentItem = (pager.currentItem + 1) % images.size })
                            pager.post { pager.currentItem = (pager.currentItem + 1) % images.size }
//                            if (f < images.size) {
//                                pager.post { pager.currentItem = (pager.currentItem + 1) % images.size - 1}
//                                f++
//                                d = f
//                                Log.e("FValue", f.toString())
//                            }
//                            else if (f == images.size) {
//                                if (d > 0) {
//                                    pager.post { pager.currentItem = (pager.currentItem - 1) % images.size }
//                                    d--
//                                    Log.e("FValue", d.toString())
//                                }
//                                else
//                                    f = 0
//                            }
//                            else
//                                f=0

                        }
                    }
                    timer = Timer()
                    timer!!.schedule(timerTask, 0, 3000)
                } else
                    Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<MainModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }


        })
//        context = this
//        pager = findViewById<ViewPager>(R.id.pager) as ViewPager
//        var adapter = Adapter(images,context)
//        pager.adapter = adapter
    }







}
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
//
//    val imageSlider = findViewById<ImageSlider>(R.id.image_slider) // init imageSlider
//
//    val imageList = ArrayList<SlideModel>() // Create image list
//    imageList.add(SlideModel("https://bit.ly/37Rn50u", "Baby Owl",ScaleTypes.CENTER_CROP))
//    imageList.add(SlideModel("https://bit.ly/2BteuF2", "Elephants and tigers may become extinct."))
//    imageList.add(SlideModel("https://bit.ly/3fLJf72", "The population of elephants is decreasing in the world."))
//
//    imageSlider.setImageList(imageList)
//
//    imageSlider.setItemClickListener(object : ItemClickListener {
//        override fun onItemSelected(position: Int) {
//            // You can listen here.
//        }
//    })
//
//    imageSlider.setItemChangeListener(object : ItemChangeListener {
//        override fun onItemChanged(position: Int) {
//            //println("Pos: " + position)
//        }
//    })
//
//    imageSlider.setTouchListener(object : TouchListener {
//        override fun onTouched(touched: ActionTypes) {
//            if (touched == ActionTypes.DOWN){
//                imageSlider.stopSliding()
//            } else if (touched == ActionTypes.UP ) {
//                imageSlider.startSliding(1000)
//            }
//        }
//    })
//}