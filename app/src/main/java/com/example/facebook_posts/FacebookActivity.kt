package com.example.facebook_posts

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook_posts.databinding.ActivityFacebookBinding
import kotlin.random.Random

class FacebookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFacebookBinding
    var users = mutableListOf(
        User("Abdallah Elsobky", R.drawable.user1),
        User("Mohamed Hamdy", R.drawable.user2),
        User("Mohamed Nasr", R.drawable.user3),
        User("Ahmed Essam", R.drawable.user4)
    )

    var postImgs = mutableListOf(
        R.drawable.post1,
        R.drawable.post2, R.drawable.post3,
        R.drawable.post4, R.drawable.post5,
        R.drawable.post6, R.drawable.post7,
    )

    val postContents = mutableListOf(
        "Enjoying the sunny day at the park! ☀️🌳",
        "Just finished a great book, highly recommend it! 📚✨",
        "Coding late into the night, fueled by coffee. ☕💻",
        "Exploring new recipes, today's experiment: homemade pizza! 🍕👩‍🍳",
        "Workout done, feeling strong and energized! 💪🔥",
        "Here's a sneak peek of my latest art project! 🎨🖌️",
        "Weekend getaway to the mountains was amazing. 🌄🌲",
        "Reflecting on the little joys in life. 💭😊",
        "Can't believe it's already January, time flies! 🗓️✨",
        "Had an inspiring conversation today, feeling motivated. 🌟🗣️"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFacebookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvStory.adapter = StoryAdapter(generateStories(20))
        binding.rvPost.adapter = PostAdapter(generatePosts(20));
        animation()

    }

    private fun animateStoryRecyclerView(dy: Int, layoutManager: LinearLayoutManager) {
        val isVisible = binding.rvStory.visibility == View.VISIBLE
        if (dy > 0 && isVisible) {
            binding.rvStory.animate()
                .alpha(0f)
                .setDuration(300)
                .withEndAction { binding.rvStory.visibility = View.GONE }
        } else if (dy < 0 && !isVisible) {
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            if (firstVisibleItemPosition == 0) {
                binding.rvStory.visibility = View.VISIBLE
                binding.rvStory.animate()
                    .alpha(1f)
                    .setDuration(300)
            }
        }
    }

    private fun animation() {
        binding.rvPost.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                animateStoryRecyclerView(dy, layoutManager)
            }
        })
    }


    private fun generatePosts(n: Int): List<Post> {
        var posts = mutableListOf<Post>()
        for (i in 0..n) {
            val rand: Int = Random.nextInt(0, 100)
            val post = Post(
                users[rand % users.size],
                postImgs[rand % postImgs.size],
                postContents[rand % postContents.size],
                "2 hours ago"
            )
            posts.add(post)
        }
        return posts;
    }

    private fun generateStories(n: Int): List<Story> {
        var stories = mutableListOf<Story>()
        for (i in 0..n) {
            val rand: Int = Random.nextInt(0, 100)
            val story = Story(
                users[rand % users.size],
                postImgs[rand % postImgs.size]
            )
            stories.add(story)
        }
        return stories
    }
}