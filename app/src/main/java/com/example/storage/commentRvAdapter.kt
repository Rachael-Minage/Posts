package com.example.storage

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.databinding.CommentsListBinding
import com.example.storage.databinding.PostItemsListBinding

class CommentRvAdapter ( var commentsList:List<Comments>):RecyclerView.Adapter<CommentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var binding =   CommentsListBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var currentComments=commentsList.get(position)
        with(holder.binding){
            tvIdlist.text=currentComments.id.toString()
            tvPostId.text=currentComments.id.toString()
            tvNameBody.text=currentComments.name
            tvBodyStructure.text=currentComments.body
//            val context =holder. itemView.context
//            .setOnClickListener {
//                val intent = Intent(context,CommentActivity::class.java)
//                intent.putExtra("POST_ID",currentComments.id)
//                context.startActivity(intent)
//
//            }
  }
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }
}


    class CommentViewHolder(var binding:CommentsListBinding): RecyclerView.ViewHolder(binding.root){

    }
