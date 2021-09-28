package com.challenge.reddittopfeed.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.challenge.reddittopfeed.data.RedditTopFeedRepository
import com.challenge.reddittopfeed.model.RedditChildren
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    private val repository = RedditTopFeedRepository()

    fun getTopFeed(dataPortion: Int): Flow<PagingData<RedditChildren>> {
        return repository.getTopFeed(dataPortion).cachedIn(viewModelScope)
    }

}