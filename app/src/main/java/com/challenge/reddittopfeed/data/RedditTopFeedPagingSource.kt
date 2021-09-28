package com.challenge.reddittopfeed.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.challenge.reddittopfeed.api.RedditTopFeedService
import com.challenge.reddittopfeed.model.RedditChildren
import retrofit2.HttpException
import java.io.IOException

class RedditTopFeedPagingSource(
    private val redditTopFeedService: RedditTopFeedService
) : PagingSource<String, RedditChildren>() {

    override val keyReuseSupported: Boolean = true


    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditChildren> {
        return try {
            val response = redditTopFeedService.getTopFeed(params.loadSize, params.key)
            val data = response.data
            LoadResult.Page(
                data.children,
                data.before,
                data.after
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<String, RedditChildren>): String? {
        return null
    }

}