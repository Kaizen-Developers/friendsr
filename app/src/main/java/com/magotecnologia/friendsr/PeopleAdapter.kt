package com.magotecnologia.friendsr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import coil.transform.CircleCropTransformation

/**
 * Created by Marco-Laptop on 15/05/2020.
 * Adaptador encargado de mostrar a las personas de la aplicacion en el RecyclerView
 */


class PeopleAdapter(var data: List<Friend>) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    private var listener: PeopleListener = object : PeopleListener {}

    fun setOnFriendListener(listener: PeopleListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_friend, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun updatePerson(friend: Friend) {
        val index = data.indexOfFirst { it.name == friend.name }
        val newList = data.toMutableList()
        newList[index] = friend
        data = newList.toList()
        notifyItemChanged(index)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val photo = view.findViewById<ImageView>(R.id.iv_friend)
        private val score = view.findViewById<RatingBar>(R.id.rb_friend_score)
        private val name = view.findViewById<TextView>(R.id.tv_friend_name)

        fun bind(friend: Friend) {
            score.rating = friend.score
            score.isEnabled = false
            name.text = friend.name
            photo.load(friend.photo) {
                transformations(CircleCropTransformation())
                scale(Scale.FILL)
            }
            itemView.setOnClickListener {
                listener.onFriendClick(data[adapterPosition])
            }
        }

    }

    interface PeopleListener {
        fun onFriendClick(friend: Friend) {}
    }

}