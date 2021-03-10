package com.nike.musicsearch.ui.songs

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.nike.musicsearch.R
import com.nike.musicsearch.api.NetworkState
import com.nike.musicsearch.base.BaseFragment
import com.nike.musicsearch.extension.gone
import com.nike.musicsearch.extension.visible
import com.nike.musicsearch.ui.adapter.songs.SongAdapter
import com.nike.musicsearch.ui.web.WebViewActivity
import com.nike.musicsearch.viewmodel.SearchSongViewModel
import kotlinx.android.synthetic.main.songs_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel



class SongsFragment : BaseFragment(), SongAdapter.OnClickListener {
    @LayoutRes
    override fun getLayoutResId() = R.layout.songs_fragment

    private val repositoryRecyclerViewAdapter = SongAdapter(this)
    private val model by viewModel<SearchSongViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
        observeViewModelData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        setupMenu(menu)
    }

    private fun setupMenu(menu: Menu) {
        val searchMenuItem = menu.findItem(R.id.action_search)
        
        val searchView = searchMenuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        
        setupSearchViewListener(searchView)
    }

    private fun setupSearchViewListener(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { model.fetchNewSongs(query) }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })
    }

    private fun setupListeners() {
        sort_songs_button.setOnClickListener{
           repositoryRecyclerViewAdapter.sortSongsByPopularity()
        }
    }
    
    private fun observeViewModelData() {
        model.songs.observe(this, Observer { 
            repositoryRecyclerViewAdapter.addSongs(it)
        })
        
        model.networkState.observe(this, Observer {
            when (it) {
                NetworkState.SUCCESS -> {
                    fragment_progress_bar.gone()
                }
                NetworkState.FAILED -> {
                    fragment_progress_bar.gone()
                }
                NetworkState.RUNNING -> {
                    fragment_progress_bar.visible()
                }
            }
        })
        
        model.clearData.observe(this, Observer { 
            repositoryRecyclerViewAdapter.clearSongs()
        })
    }

    private fun setupRecyclerView() {
        fragment_songs_recycler_view.adapter = repositoryRecyclerViewAdapter
        fragment_songs_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    model.fetchNewSongs()
                }
            }
        })
    }
    

    //Override onRetryClick from RecipeAdapter.onRecipeRowClicked
    override fun onSongRowClicked(url: String) {
        activity?.let { WebViewActivity.createIntent(it, url) }?.let { openActivity(it) }
    }
    
}

