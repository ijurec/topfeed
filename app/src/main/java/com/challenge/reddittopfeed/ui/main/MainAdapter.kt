package com.challenge.reddittopfeed.ui.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.challenge.reddittopfeed.data.model.RedditChildren
import com.challenge.reddittopfeed.databinding.TopFeedItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.RedditTopFeedViewHolder>() {

    private var redditChildren = mutableListOf<RedditChildren>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditTopFeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TopFeedItemBinding.inflate(inflater, parent, false)

        return RedditTopFeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RedditTopFeedViewHolder, position: Int) {
        val item = redditChildren[position]
        holder.bind(item)
    }

    override fun getItemCount() = redditChildren.size

    fun setTopFeedList(redditChildren: List<RedditChildren>) {
        this.redditChildren.clear()
        this.redditChildren = redditChildren.toMutableList()
        notifyDataSetChanged()
    }

    class RedditTopFeedViewHolder(private val binding: TopFeedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(
                    binding.root.context,
                    Uri.parse("https://www.reddit.com" + binding.root.tag.toString())
                )
            }
        }

        fun bind(redditItem: RedditChildren) {
            binding.apply {
                textViewAuthor.text = redditItem.data.author
                Glide.with(imageViewThumbnail.context)
                    .load(redditItem.data.thumbnailUrl)
                    .into(imageViewThumbnail)

                binding.root.tag = redditItem.data.permalink
            }
        }
    }

}