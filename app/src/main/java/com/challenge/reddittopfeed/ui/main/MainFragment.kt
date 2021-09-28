package com.challenge.reddittopfeed.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.reddittopfeed.api.RedditTopFeedService
import com.challenge.reddittopfeed.data.RedditTopFeedRepository
import com.challenge.reddittopfeed.databinding.MainFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private val topFeedLoadPortion: Int = 5
    private lateinit var mainFragmentBinding: MainFragmentBinding
    private lateinit var mainAdapter: MainAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)
        mainFragmentBinding.redditTopFeedRecyclerView.layoutManager = LinearLayoutManager(activity)
        mainFragmentBinding.redditTopFeedRecyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        mainAdapter = MainAdapter()

        mainFragmentBinding.redditTopFeedRecyclerView.adapter = mainAdapter
        mainFragmentBinding.redditTopFeedRecyclerView.adapter =
            mainAdapter.withLoadStateFooter(
//                header = MainLoadStateAdapter { mainAdapter.retry() },
                footer = MainLoadStateAdapter { mainAdapter.retry() }
            )
        return mainFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        getTopFeed()
    }

    private fun getTopFeed() {
        lifecycleScope.launch {
            viewModel.getTopFeed(topFeedLoadPortion).collectLatest { pagingData ->
                mainAdapter.submitData(pagingData)
            }
        }
    }

}