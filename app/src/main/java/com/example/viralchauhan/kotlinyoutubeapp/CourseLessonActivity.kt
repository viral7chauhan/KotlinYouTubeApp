package com.example.viralchauhan.kotlinyoutubeapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_course_lesson.*

/**
 * Created by viralchauhan on 02/01/18.
 */
class CourseLessonActivity : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_course_lesson)

        webview_course_lesson.settings.javaScriptEnabled = true
        webview_course_lesson.settings.loadWithOverviewMode = true
        webview_course_lesson.settings.useWideViewPort = true

        webview_course_lesson.webViewClient = WebViewClient()

        val title = intent.getStringExtra(CourseLessonViewHolder.COURSE_NAME)
        supportActionBar?.title = title

        val courseLessonUrl = intent.getStringExtra(CourseLessonViewHolder.COURSE_LESSON_URL)

        println(courseLessonUrl)
        webview_course_lesson.loadUrl(courseLessonUrl)

    }
}