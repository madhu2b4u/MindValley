package com.demo.mindvalley.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.mindvalley.LiveDataTestUtil
import com.demo.mindvalley.MainCoroutineRule
import com.demo.mindvalley.TestUtils
import com.demo.mindvalley.common.Status
import com.demo.mindvalley.main.data.local.source.LocalDataSource
import com.demo.mindvalley.main.data.remote.source.MainRemoteDataSource
import com.demo.mindvalley.main.data.repository.MainRepository
import com.demo.mindvalley.main.data.repository.MainRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class MainRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repository: MainRepository

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: MainRemoteDataSource

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = MainRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun testGetCategoriesFromAPI() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(localDataSource.getCategories()).thenReturn(null)
        Mockito.`when`(remoteDataSource.getCategories()).thenReturn(TestUtils().fakeCategories)
        val result = repository.getCategories(false)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == TestUtils().fakeCategories)
    }

    @Test
    fun testGetCategoriesFromDb() = mainCoroutineRule.runBlockingTest {

        Mockito.`when`(localDataSource.getCategories()).thenReturn(TestUtils().fakeCategories)
        val result = repository.getCategories(false)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        kotlinx.coroutines.delay(2000)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == TestUtils().fakeCategories)
    }

    @Test(expected = Exception::class)
    fun testGetCategoriesThrowException() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(localDataSource.getCategories()).thenReturn(null)
        Mockito.doThrow(Exception("error")).`when`(remoteDataSource.getCategories())
        repository.getCategories(false)

    }


    @Test
    fun testGetChannelsFromAPI() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(localDataSource.getChannels()).thenReturn(null)
        Mockito.`when`(remoteDataSource.getChannels()).thenReturn(TestUtils().fakeChannels)
        val result = repository.getChannels(false)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == TestUtils().fakeChannels)
    }

    @Test
    fun testGetChannelsFromDb() = mainCoroutineRule.runBlockingTest {

        Mockito.`when`(localDataSource.getChannels()).thenReturn(TestUtils().fakeChannels)
        val result = repository.getChannels(false)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        kotlinx.coroutines.delay(2000)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == TestUtils().fakeChannels)
    }

    @Test(expected = Exception::class)
    fun testGetChannelsThrowException() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(localDataSource.getChannels()).thenReturn(null)
        Mockito.doThrow(Exception("error")).`when`(remoteDataSource.getChannels())
        repository.getChannels(false)

    }


    @Test
    fun testGetEpisodesFromAPI() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(localDataSource.getEpisodes()).thenReturn(null)
        Mockito.`when`(remoteDataSource.getEpisodes()).thenReturn(TestUtils().fakeMedias)
        val result = repository.getEpisodes(false)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == TestUtils().fakeMedias)
    }

    @Test
    fun testGetEpisodesFromDb() = mainCoroutineRule.runBlockingTest {

        Mockito.`when`(localDataSource.getEpisodes()).thenReturn(TestUtils().fakeMedias)
        val result = repository.getEpisodes(false)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        kotlinx.coroutines.delay(2000)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == TestUtils().fakeMedias)
    }

    @Test(expected = Exception::class)
    fun testGetEpisodesThrowException() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(localDataSource.getEpisodes()).thenReturn(null)
        Mockito.doThrow(Exception("error")).`when`(remoteDataSource.getEpisodes())
        repository.getEpisodes(false)

    }


}
