package com.demo.mindvalley.main.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.mindvalley.R
import com.demo.mindvalley.assemblyrecyclerview.RecyclerViewAssemblerAdapter
import com.demo.mindvalley.assemblyrecyclerview.RecyclerViewAssemblerElement
import com.demo.mindvalley.common.Status
import com.demo.mindvalley.common.Utils
import com.demo.mindvalley.common.ViewModelFactory
import com.demo.mindvalley.main.data.models.categoriesmodel.Category
import com.demo.mindvalley.main.data.models.channelmodel.Channel
import com.demo.mindvalley.main.data.models.episodemodel.Media
import com.demo.mindvalley.main.presentation.ui.adapters.CategoryRecyclerviewAdapter
import com.demo.mindvalley.main.presentation.ui.adapters.ChannelRecyclerviewAdapter
import com.demo.mindvalley.main.presentation.ui.adapters.EpisodeRecyclerviewAdapter
import com.demo.mindvalley.main.presentation.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private var categories: List<Category> = ArrayList()
    private var episodes: List<Media> = ArrayList()
    private var channels: List<Channel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            mainViewModel =
                ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)

            mainViewModel.categoryResult.observe(this@MainFragment, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { categoriesList ->
                            categories = categoriesList
                            rvCategories.apply {
                                setHasFixedSize(true)
                                layoutManager = GridLayoutManager(context, 2)
                                adapter = CategoryRecyclerviewAdapter(categories)
                            }

                        }
                    }

                }
            })

            mainViewModel.episodeResult.observe(this@MainFragment, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { episodeList ->
                            episodes = episodeList
                            rvEpisodes.apply {
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                                adapter = EpisodeRecyclerviewAdapter(episodes)
                            }
                        }
                    }

                }
            })

            mainViewModel.channelResult.observe(this@MainFragment, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { channelList ->
                            channels = channelList
                            rvChannels.apply {
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context)
                                adapter = ChannelRecyclerviewAdapter(channels)
                            }
                        }
                    }

                }
            })

            initViews()
        }
    }


    private fun initViews() {
        swipeRefresh.setOnRefreshListener {
            showSnack()
            swipeRefresh.isRefreshing = false
            mainViewModel.loadCategories(true)
            mainViewModel.loadChannels(true)
            mainViewModel.loadEpisodes(true)
        }

    }

    private fun showSnack() {
        if (!context?.let { Utils().isNetworkAvailable(it) }!!) {
            val snack = view?.let {
                Snackbar.make(
                    it,
                    getString(R.string.no_internet),
                    Snackbar.LENGTH_LONG
                )
            }
            snack?.show()
        }
    }


}
