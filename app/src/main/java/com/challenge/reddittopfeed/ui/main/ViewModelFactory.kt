package com.challenge.reddittopfeed.ui.main

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.challenge.reddittopfeed.data.RedditTopFeedRepository

class ViewModelFactory {

//    class ViewModelFactory(
//        owner: SavedStateRegistryOwner,
//        private val repository: RedditTopFeedRepository
//    ) : AbstractSavedStateViewModelFactory(owner, null) {
//
//        override fun <T : ViewModel?> create(
//            key: String,
//            modelClass: Class<T>,
//            handle: SavedStateHandle
//        ): T {
//            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//                @Suppress("UNCHECKED_CAST")
//                return MainViewModel(repository, handle) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }


}