package com.challenge.reddittopfeed.data.model

import com.google.gson.annotations.SerializedName

data class RedditTopFeed(val data: RedditData)

data class RedditData(val after: String, val children: List<RedditChildren>)

data class RedditChildren(val data: RedditChildrenData)

data class RedditChildrenData(
    val title: String,
    val author: String,
    val subreddit: String,
    val postDate: String,
    @SerializedName("thumbnail")
    val thumbnailUrl: String,
    val currentRating: String,
    @SerializedName("num_comments")
    val numberOfComments: String,
    val permalink: String
)
