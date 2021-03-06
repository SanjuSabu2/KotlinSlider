package com.example.kotlinslider.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.kotlinslider.R

class Adapter(var images: ArrayList<String>, var ctx: Context): PagerAdapter() {
    lateinit var layoutInflater: LayoutInflater
    lateinit var context: Context

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = LayoutInflater.from(ctx)
        var view = layoutInflater.inflate(R.layout.item, container, false)
        val img = view.findViewById<ImageView>(R.id.img)
        Glide.with(ctx).load(images[position]).into(img)
        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}