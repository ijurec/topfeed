package com.challenge.reddittopfeed.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.challenge.reddittopfeed.data.remote.Response
import com.challenge.reddittopfeed.data.repository.RedditTopFeedRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {

    fun getTopFeed(limit: String, after: String) = liveData(Dispatchers.IO) {
        try {
            emit(Response.inProgress())
            emit(Response.success(RedditTopFeedRepository().getTopFeed(limit, after)))
        } catch (exception: Exception) {
            emit(Response.error(exception.message ?: "There is some network issue"))
        }
    }

}