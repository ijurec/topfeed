package com.challenge.reddittopfeed.model

import com.google.gson.annotations.SerializedName

data class RedditTopFeed(val data: RedditData)

data class RedditData(var after: String?, var before: String?, val children: List<RedditChildren>)

data class RedditChildren(val data: RedditChildrenData)

data class RedditChildrenData(
    val title: String,
    val author: String,
    val subreddit: String,
    @SerializedName("created")
    val postDate: String,
    @SerializedName("thumbnail")
    val thumbnailUrl: String,
    val score: String,
    @SerializedName("num_comments")
    val numberOfComments: String,
    val permalink: String
)
