package com.challenge.reddittopfeed.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.challenge.reddittopfeed.api.RedditTopFeedService
import com.challenge.reddittopfeed.model.RedditChildren
import kotlinx.coroutines.flow.Flow

class RedditTopFeedRepository {

    fun getTopFeed(limit: Int): Flow<PagingData<RedditChildren>> {
        return Pager(
            config = PagingConfig(
                pageSize = limit,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = {
                RedditTopFeedPagingSource(
                    RedditTopFeedService.create()
                )
            }
        ).flow
    }

}