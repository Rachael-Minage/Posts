package com.example.storage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.databinding.PostItemsListBinding


class PostAdapter ( var context:Context, var postList:List<Posts>):RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var binding =PostItemsListBinding
            .inflate(LayoutInflater.from(context),parent,false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currentPost=postList.get(position)
        with(holder.binding){
            tvUserinfo.text=currentPost.userid.toString()
            tvinfo.text=currentPost.id.toString()
            tvTitleInfo.text=currentPost.title
            tvBodyInfo.text=currentPost.body
        }

    }

    override fun getItemCount(): Int {
        return postList.size
    }
}
class PostViewHolder(var binding:PostItemsListBinding):RecyclerView.ViewHolder(binding.root){

}