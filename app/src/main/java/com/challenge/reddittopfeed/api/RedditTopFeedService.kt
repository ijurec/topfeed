package com.challenge.reddittopfeed.api

import com.challenge.reddittopfeed.model.RedditTopFeed
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditTopFeedService {

    @GET("top.json")
    suspend fun getTopFeed(
        @Query("limit") limit: Int,
        @Query("after") after: String? = null
    ): RedditTopFeed

    companion object {

        const val BASE_URL = "https://www.reddit.com"

        fun create(): RedditTopFeedService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(RedditTopFeedService::class.java)
        }
    }

}
