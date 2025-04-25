package com.fauzangifari.yournime.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fauzangifari.domain.model.Anime
import com.fauzangifari.yournime.R

class UpcomingAnimeAdapter(
    private val onItemClick: (Anime) -> Unit
) : ListAdapter<Anime, UpcomingAnimeAdapter.AnimeViewHolder>(AnimeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = getItem(position)
        holder.bind(anime)
    }

    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgAnime: ImageView = itemView.findViewById(R.id.img_anime)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)

        fun bind(anime: Anime) {
            tvTitle.text = anime.title
            Glide.with(itemView.context)
                .load(anime.images)
                .into(imgAnime)

            itemView.setOnClickListener {
                onItemClick(anime)
            }
        }
    }

    class AnimeDiffCallback : DiffUtil.ItemCallback<Anime>() {
        override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean =
            oldItem == newItem
    }
}
