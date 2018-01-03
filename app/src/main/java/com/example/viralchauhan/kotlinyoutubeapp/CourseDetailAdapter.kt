package com.example.viralchauhan.kotlinyoutubeapp

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.course_detail_row.view.*

/**
 * Created by viralchauhan on 28/12/17.
 */

class  CourseDetailAdapter(val courseLessons: Array<CourseLesson>) : RecyclerView.Adapter<CourseLessonViewHolder> () {

    override fun getItemCount(): Int {
        return courseLessons.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CourseLessonViewHolder {
        val inflator = LayoutInflater.from(parent?.context)
        val customView = inflator.inflate(R.layout.course_detail_row, parent, false)
        return  CourseLessonViewHolder(customView)
    }

    override fun onBindViewHolder(holder: CourseLessonViewHolder?, position: Int) {
        val courseLesson = courseLessons.get(position)

        holder?.customView?.textView_course_title?.text = courseLesson.name
        holder?.customView?.textView_duration?.text = courseLesson.duration

        Picasso.with(holder?.customView?.context).load(courseLesson.imageUrl).into(holder?.customView?.imageView_course_thumbnail)
        holder?.courseLesson = courseLesson
    }
}

class CourseLessonViewHolder(val customView: View, var courseLesson: CourseLesson? = null): RecyclerView.ViewHolder(customView) {

    companion object {
        val COURSE_LESSON_URL = "COURSE_URL"
        val COURSE_NAME = "COURSE_NAME"
    }
    init {
        customView.setOnClickListener {
            println("Attempt to click")
            val intent = Intent(customView.context, CourseLessonActivity::class.java)

            intent.putExtra(COURSE_NAME, courseLesson?.name)
            intent.putExtra(COURSE_LESSON_URL, courseLesson?.link)

            customView.context.startActivity(intent)
        }
    }
}