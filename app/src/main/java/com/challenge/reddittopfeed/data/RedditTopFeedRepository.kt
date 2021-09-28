package com.challenge.reddittopfeed.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.challenge.reddittopfeed.api.RedditTopFeedService
import com.challenge.reddittopfeed.model.RedditChildren
import kotlinx.coroutines.flow.Flow

class RedditTopFeedRepository {

    fun getTopFeed(dataPortion: Int): Flow<PagingData<RedditChildren>> {
        return Pager(
            config = PagingConfig(
                pageSize = dataPortion,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                RedditTopFeedPagingSource(
                    RedditTopFeedService.create()
                )
            }
        ).flow
    }

}