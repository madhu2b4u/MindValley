package com.demo.mindvalley.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.mindvalley.MainCoroutineRule
import com.demo.mindvalley.main.data.local.database.MediaDao
import com.demo.mindvalley.main.data.local.source.LocalDataSource
import com.demo.mindvalley.main.data.local.source.LocalDataSourceImpl
import com.demo.mindvalley.main.data.mapper.CategoryMapper
import com.demo.mindvalley.main.data.mapper.ChannelMapper
import com.demo.mindvalley.main.data.mapper.EpisodeMapper
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainLocalDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var dao: MediaDao


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)

        localDataSource = LocalDataSourceImpl(
            dao,
            CategoryMapper(Gson()), ChannelMapper(Gson()), EpisodeMapper(Gson()),
            mainCoroutineRule.coroutineContext
        )
    }


    @Test
    fun testInvalidEntityUsers() = mainCoroutineRule.runBlockingTest {

        Mockito.`when`(dao.getCategories()).thenReturn(null)
        Mockito.`when`(dao.getChannels()).thenReturn(null)
        Mockito.`when`(dao.getEpisodes()).thenReturn(null)

        val categoryResult = localDataSource.getCategories()
        val episodeResult = localDataSource.getEpisodes()
        val channelResult = localDataSource.getChannels()

        assert(categoryResult == null)
        assert(episodeResult == null)
        assert(channelResult == null)

    }


}