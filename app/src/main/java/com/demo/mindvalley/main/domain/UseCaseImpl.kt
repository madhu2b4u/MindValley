package com.demo.mindvalley.main.domain

import com.demo.mindvalley.main.data.repository.MainRepository
import javax.inject.Inject

class UseCaseImpl @Inject constructor(private val mMainRepository: MainRepository) : UseCase {

    override suspend fun getEpisodes(mustFetchFromNetwork: Boolean) =
        mMainRepository.getEpisodes(mustFetchFromNetwork)

    override suspend fun getChannels(mustFetchFromNetwork: Boolean) =
        mMainRepository.getChannels(mustFetchFromNetwork)

    override suspend fun getCategories(mustFetchFromNetwork: Boolean) =
        mMainRepository.getCategories(mustFetchFromNetwork)

}
