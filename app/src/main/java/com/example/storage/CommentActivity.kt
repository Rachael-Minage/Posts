package com.example.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storage.databinding.ActivityCommentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentActivity : AppCompatActivity() {
    lateinit var binding:ActivityCommentBinding
    var postId= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPost()
        fetchPosts()
        getComments()

    }

    fun obtainPost(){
        postId = intent.extras?.getInt("POST_ID")?:0
    }

    fun fetchPosts(){
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getPostById(postId)

        request.enqueue(object :Callback<Posts>{
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                var post = response.body()
                if (post!=null){
                    binding.tvPost.text = post.body
                    binding.tvPostTitle.text= post.title

                }

            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
            }
        })
    }

    fun getComments(){
        val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = retrofit.getComments()

        request.enqueue(object : Callback<List<Comments>>{
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                if(response.isSuccessful){
                    val comments = response.body()
                    if (comments != null) {
                        displayComments(comments)
                    }

                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {

            }

})
}
    fun displayComments(commentList: List<Comments>) {
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        val commentsAdapter = CommentRvAdapter(commentList)
        binding.rvComments.adapter = commentsAdapter //called it
    }
}