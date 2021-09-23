package com.challenge.reddittopfeed.data.repository

import com.challenge.reddittopfeed.data.remote.RedditTopFeedService

class RedditTopFeedRepository {

    suspend fun getTopFeed(limit: String, after: String) =
        RedditTopFeedService.create().getTopFeed(limit, after)

}