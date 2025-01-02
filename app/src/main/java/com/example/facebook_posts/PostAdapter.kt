package com.example.facebook_posts

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class PostAdapter(private val posts: List<Post>) : Adapter<PostAdapter.PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false)
        return PostViewHolder(view)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class PostViewHolder(view: View) : ViewHolder(view) {
        var userName = view.findViewById<TextView>(R.id.user_name)
        var userImage = view.findViewById<ImageView>(R.id.profile_img)
        var postDate = view.findViewById<TextView>(R.id.post_date)
        var postText = view.findViewById<TextView>(R.id.post_text)
        var postImage = view.findViewById<ImageView>(R.id.post_img)

        fun bind(post: Post) {
            userName.text = post.User.Name
            userImage.setImageResource(post.User.Image)
            postDate.text = post.Time
            postText.text = post.Content
            postImage.setImageResource(post.Image)
        }
    }
}