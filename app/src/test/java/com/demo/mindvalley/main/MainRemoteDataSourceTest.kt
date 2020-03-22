package com.demo.mindvalley.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.mindvalley.MainCoroutineRule
import com.demo.mindvalley.TestUtils
import com.demo.mindvalley.main.data.models.categoriesmodel.CategoriesResponse
import com.demo.mindvalley.main.data.models.channelmodel.ChannelsResponse
import com.demo.mindvalley.main.data.models.episodemodel.EpisodesResponse
import com.demo.mindvalley.main.data.remote.services.MainService
import com.demo.mindvalley.main.data.remote.source.MainRemoteDataSource
import com.demo.mindvalley.main.data.remote.source.MainRemoteDataSourceImpl
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MainRemoteDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var remoteDataSource: MainRemoteDataSource
    private lateinit var service: MainService

    private val fakeEpisodesResponse = TestUtils().fakeEpisodesResponse
    private val fakeChannelsResponse = TestUtils().fakeChannelResponse
    private val fakeCategoryResponse = TestUtils().fakeCategoryResponse

    @Before
    fun init() {

        service = mock {
            onBlocking { getCategoriesAsync() } doReturn GlobalScope.async {
                Response.success(fakeCategoryResponse)
            }
        }

        remoteDataSource = MainRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)
    }

    @Test
    fun testGetCategories() = runBlocking {

        service = mock {
            onBlocking { getCategoriesAsync() } doReturn GlobalScope.async {
                Response.success(fakeCategoryResponse)
            }
        }

        remoteDataSource =
            MainRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        // Will be launched in the mainThreadSurrogate dispatcher
        val result = remoteDataSource.getCategories()

        assert(result == fakeCategoryResponse.data.categories)
    }

    @Test(expected = Exception::class)
    fun testCategoriesThrowUserException() = runBlocking {

        service = mock {
            onBlocking { getCategoriesAsync() } doReturn GlobalScope.async {
                Response.error<CategoriesResponse>(404, null)
            }
        }

        remoteDataSource =
            MainRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        assert(remoteDataSource.getCategories() == fakeCategoryResponse.data.categories)

    }


    @Test
    fun testGetChannels() = runBlocking {

        service = mock {
            onBlocking { getChannelsAsync() } doReturn GlobalScope.async {
                Response.success(fakeChannelsResponse)
            }
        }

        remoteDataSource = MainRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        // Will be launched in the mainThreadSurrogate dispatcher
        val result = remoteDataSource.getChannels()

        assert(result == fakeChannelsResponse.data.channels)


    }

    @Test(expected = Exception::class)
    fun testChannelsThrowUserException() = runBlocking {

        service = mock {
            onBlocking { getChannelsAsync() } doReturn GlobalScope.async {
                Response.error<ChannelsResponse>(404, null)
            }
        }

        remoteDataSource = MainRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        assert(remoteDataSource.getCategories() == fakeChannelsResponse.data.channels)

    }

    @Test
    fun testGetEpisodes() = runBlocking {

        service = mock {
            onBlocking { getEpisodesAsync() } doReturn GlobalScope.async {
                Response.success(fakeEpisodesResponse)
            }
        }

        remoteDataSource = MainRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        // Will be launched in the mainThreadSurrogate dispatcher
        val result = remoteDataSource.getEpisodes()

        assert(result == fakeEpisodesResponse.data.media)


    }

    @Test(expected = Exception::class)
    fun testEpisodesThrowUserException() = runBlocking {

        service = mock {
            onBlocking { getEpisodesAsync() } doReturn GlobalScope.async {
                Response.error<EpisodesResponse>(404, null)
            }
        }

        remoteDataSource = MainRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        assert(remoteDataSource.getEpisodes() == fakeEpisodesResponse.data.media)

    }


}