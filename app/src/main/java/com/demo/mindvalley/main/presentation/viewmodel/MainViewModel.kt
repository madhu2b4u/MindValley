package com.demo.mindvalley.main.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.mindvalley.common.Result
import com.demo.mindvalley.main.data.models.categoriesmodel.Category
import com.demo.mindvalley.main.data.models.channelmodel.Channel
import com.demo.mindvalley.main.data.models.episodemodel.Media
import com.demo.mindvalley.main.domain.UseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(private val mUseCase: UseCase) : ViewModel() {

    val channelResult = MediatorLiveData<Result<List<Channel>>>()
    val episodeResult = MediatorLiveData<Result<List<Media>>>()
    val categoryResult = MediatorLiveData<Result<List<Category>>>()


    init {
        loadChannels()
        loadCategories()
        loadEpisodes()
    }

    fun loadChannels(mustFetchFromNetwork: Boolean = false) {
        viewModelScope.launch {
            channelResult.addSource(mUseCase.getChannels(mustFetchFromNetwork)) {
                channelResult.value = it
            }
        }
    }

    fun loadCategories(mustFetchFromNetwork: Boolean = false) {
        viewModelScope.launch {
            categoryResult.addSource(mUseCase.getCategories(mustFetchFromNetwork)) {
                categoryResult.value = it
            }
        }
    }

    fun loadEpisodes(mustFetchFromNetwork: Boolean = false) {
        viewModelScope.launch {
            episodeResult.addSource(mUseCase.getEpisodes(mustFetchFromNetwork)) {
                episodeResult.value = it
            }
        }
    }



}