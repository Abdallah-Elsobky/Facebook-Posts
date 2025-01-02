package com.example.facebook_posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class StoryAdapter(private val stories: List<Story>) : Adapter<StoryAdapter.StoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.story_layout,parent,false)
        return StoryViewHolder(view)
    }


    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = stories[position]
        holder.bind(story)
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    class StoryViewHolder(view: View) : ViewHolder(view) {
        var userName=view.findViewById<TextView>(R.id.user_name)
        var userImage=view.findViewById<ImageView>(R.id.user_img)
        var storyImage=view.findViewById<ImageView>(R.id.story_img)

        fun bind(story: Story) {
            userName.text=story.User.Name
            userImage.setImageResource(story.User.Image)
            storyImage.setImageResource(story.Image)
        }
    }
}