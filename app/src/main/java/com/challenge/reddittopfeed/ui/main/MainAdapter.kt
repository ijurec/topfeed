package com.challenge.reddittopfeed.ui.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.challenge.reddittopfeed.R
import com.challenge.reddittopfeed.model.RedditChildren
import com.challenge.reddittopfeed.databinding.TopFeedItemBinding
import com.challenge.reddittopfeed.utils.TimeUtil

class MainAdapter :
    PagingDataAdapter<RedditChildren, MainAdapter.RedditTopFeedViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditTopFeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TopFeedItemBinding.inflate(inflater, parent, false)

        return RedditTopFeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RedditTopFeedViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
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
                with(redditItem.data) {
                    textViewAuthor.text = "Posted by " + redditItem.data.author
                    textViewTitle.text = title
                    textViewRating.text =
                        if (score.toInt() < 1000) {
                            score
                        } else (score.toInt() / 1000).toString() + "k"

                    textViewTime.text = TimeUtil.getTimeAgo(postDate)
                    
                    Glide.with(imageViewThumbnail.context)
                        .load(thumbnailUrl)
                        .error(R.drawable.ic_launcher_background)
                        .into(imageViewThumbnail)

                    binding.root.tag = permalink
                }
            }
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<RedditChildren>() {
            override fun areItemsTheSame(
                oldItem: RedditChildren,
                newItem: RedditChildren
            ): Boolean =
                oldItem.data == newItem.data

            override fun areContentsTheSame(
                oldItem: RedditChildren,
                newItem: RedditChildren
            ): Boolean =
                oldItem.data.title == newItem.data.title &&
                        oldItem.data.author == newItem.data.author &&
                        oldItem.data.numberOfComments == newItem.data.numberOfComments
        }
    }

}