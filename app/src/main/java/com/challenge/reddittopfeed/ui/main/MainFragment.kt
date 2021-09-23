package com.challenge.reddittopfeed.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.reddittopfeed.R
import com.challenge.reddittopfeed.data.remote.Response
import com.challenge.reddittopfeed.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val topFeedLimit: Int = 50
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

        return mainFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getTopFeed(topFeedLimit.toString(), "").observe(viewLifecycleOwner, {
            it?.apply {
                when (this.responseStatus) {
                    Response.Status.SUCCESS -> {
                        responseBody?.data?.children?.apply {
                            mainAdapter.setTopFeedList(this)
                            mainFragmentBinding.redditTopFeedRecyclerView.isVisible = true
                            mainFragmentBinding.redditTopFeedProgressBar.isVisible = false
                            mainFragmentBinding.redditTopFeedTextView.isVisible = false
                        }
                    }

                    Response.Status.ERROR -> {
                        errorMessage.apply {
                            mainFragmentBinding.redditTopFeedTextView.text = this
                            mainFragmentBinding.redditTopFeedRecyclerView.isVisible = false
                            mainFragmentBinding.redditTopFeedProgressBar.isVisible = false
                            mainFragmentBinding.redditTopFeedTextView.isVisible = true
                        }
                    }

                    Response.Status.IN_PROGRESS -> {
                        mainFragmentBinding.redditTopFeedRecyclerView.isVisible = false
                        mainFragmentBinding.redditTopFeedProgressBar.isVisible = true
                        mainFragmentBinding.redditTopFeedTextView.isVisible = false
                    }
                }
            }
        })
    }

}