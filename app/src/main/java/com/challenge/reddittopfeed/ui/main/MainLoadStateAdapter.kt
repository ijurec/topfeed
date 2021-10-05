package com.challenge.reddittopfeed.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.challenge.reddittopfeed.R
import com.challenge.reddittopfeed.databinding.RedditTopFeedLoadStateFooterViewItemBinding

class MainLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MainLoadStateAdapter.RedditTopFeedLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: RedditTopFeedLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): RedditTopFeedLoadStateViewHolder {
        return RedditTopFeedLoadStateViewHolder.create(parent, retry)
    }

    class RedditTopFeedLoadStateViewHolder(
        private val binding: RedditTopFeedLoadStateFooterViewItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.footerRetryButton.also {
                it.setOnClickListener { retry.invoke() }
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                if (loadState is LoadState.Error) {
                    footerErrorMessage.text = loadState.error.localizedMessage
                }
                footerProgressBar.isVisible = loadState is LoadState.Loading
                footerRetryButton.isVisible = loadState is LoadState.Error
                footerErrorMessage.isVisible = loadState is LoadState.Error
            }
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): RedditTopFeedLoadStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.reddit_top_feed_load_state_footer_view_item, parent, false)
                return RedditTopFeedLoadStateViewHolder(
                    RedditTopFeedLoadStateFooterViewItemBinding.bind(
                        view
                    ), retry
                )
            }
        }
    }
}

