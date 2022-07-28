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
                    Toast.makeText(baseContext,"fetched ${posts!!.size}posts",
                    Toast.LENGTH_LONG).show()
                    binding.rvPost.layoutManager=LinearLayoutManager(baseContext)
                    binding.rvPost.adapter=PostAdapter(baseContext,posts)
                }
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {

            }
        }

        )
    }
}
