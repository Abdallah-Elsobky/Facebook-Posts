package com.example.facebook_posts

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.facebook_posts.databinding.ActivityFacebookBinding
import kotlin.random.Random

class FacebookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFacebookBinding
    private val users = mutableListOf(
        User("Abdallah Elsobky", R.drawable.user1,true),
        User("Mohamed Hamdy", R.drawable.user2,false),
        User("Mohamed Nasr", R.drawable.user3,false),
        User("Ahmed Essam", R.drawable.user4,true)
    )

    private val postImgs = mutableListOf(
        R.drawable.post1, R.drawable.post2, R.drawable.post3,
        R.drawable.post4, R.drawable.post5, R.drawable.post6, R.drawable.post7
    )

    private val postContents = mutableListOf(
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

    @SuppressLint("ResourceAsColor")
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

        binding.rvStory.adapter = StoryAdapter(generateStories(50))
        binding.rvPost.adapter = PostAdapter(generatePosts(50))
        scrolling()
    }

    private fun scrolling() {
        binding.rvPost.isNestedScrollingEnabled = false
        binding.nestedScrollView.viewTreeObserver.addOnScrollChangedListener {
            val scrollView = binding.nestedScrollView
            val recyclerView = binding.rvPost

            if (scrollView.getChildAt(scrollView.childCount - 1).bottom <= scrollView.scrollY + scrollView.height) {
                recyclerView.isNestedScrollingEnabled = true
            } else {
                recyclerView.isNestedScrollingEnabled = false
            }
        }
    }

    private fun generatePosts(n: Int): List<Post> {
        val posts = mutableListOf<Post>()
        for (i in 0 until n) {
            val rand = Random.nextInt(0, 100)
            val post = Post(
                users[rand % users.size],
                postImgs[rand % postImgs.size],
                postContents[rand % postContents.size],
                "2 hours ago"
            )
            posts.add(post)
        }
        return posts
    }

    private fun generateStories(n: Int): List<Story> {
        val stories = mutableListOf<Story>()
        for (i in 0 until n) {
            val rand = Random.nextInt(0, 100)
            val story = Story(
                users[rand % users.size],
                postImgs[rand % postImgs.size]
            )
            stories.add(story)
        }
        return stories
    }
}
