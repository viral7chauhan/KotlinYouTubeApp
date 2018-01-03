package com.example.viralchauhan.kotlinyoutubeapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

/**
 * Created by viralchauhan on 28/12/17.
 */

class  CourseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val title = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = title

        fetchJSON()
        recyclerView_main.layoutManager = LinearLayoutManager(this)

    }

    private fun fetchJSON () {
        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY, -1)
        //https://api.letsbuildthatapp.com/youtube/course_detail?id=1
        val courseDetailUrl  = "https://api.letsbuildthatapp.com/youtube/course_detail?id=" + videoId
        println(courseDetailUrl)

        val client = OkHttpClient()
        val request = Request.Builder().url(courseDetailUrl).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                val gson = GsonBuilder().create()
                val courseLessons = gson.fromJson(body, Array<CourseLesson> :: class.java)

                runOnUiThread {
                    recyclerView_main.adapter = CourseDetailAdapter(courseLessons)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {

            }
        })

    }

}