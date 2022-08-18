package com.example.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storage.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getPosts()
    }
    fun getPosts(){
        val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = retrofit.getPosts()

        request.enqueue(object : Callback<List<Posts>>{
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if(response.isSuccessful){
                    val posts = response.body()
                    if (posts != null){
                        displayPosts(posts)
                    }

                }
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {

            }
        })
    }
    fun displayPosts(postsList: List<Posts>) {
        binding.rvPost.layoutManager = LinearLayoutManager(this)
        val postsAdapter = PostAdapter(postsList)
        binding.rvPost.adapter = postsAdapter //called it
    }
}



