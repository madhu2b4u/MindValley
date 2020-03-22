package com.demo.mindvalley.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.demo.mindvalley.LiveDataTestUtil
import com.demo.mindvalley.MainCoroutineRule
import com.demo.mindvalley.TestUtils
import com.demo.mindvalley.common.Result
import com.demo.mindvalley.common.Status
import com.demo.mindvalley.main.data.models.categoriesmodel.Category
import com.demo.mindvalley.main.data.models.channelmodel.Channel
import com.demo.mindvalley.main.data.models.episodemodel.Media
import com.demo.mindvalley.main.data.repository.MainRepository
import com.demo.mindvalley.main.domain.UseCase
import com.demo.mindvalley.main.domain.UseCaseImpl
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var useCase: UseCase

    lateinit var repository: MainRepository

    @Test
    fun testEpisodesLoadingData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getEpisodes(false) } doReturn object :
                LiveData<Result<List<Media>>>() {
                init {
                    value = Result.loading()
                }
            }
        }
        useCase = UseCaseImpl(repository)

        val result = useCase.getEpisodes()
        result.observeForever { }
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)

    }


    @Test
    fun testEpisodesSuccessData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getEpisodes(false) } doReturn object :
                LiveData<Result<List<Media>>>() {
                init {
                    value = Result.success(TestUtils().fakeMedias)
                }
            }
        }
        useCase = UseCaseImpl(repository)

        val result = useCase.getEpisodes()

        result.observeForever { }

        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS && LiveDataTestUtil.getValue(
                result
            ).data == TestUtils().fakeMedias
        )

    }

    @Test
    fun testEpisodesErrorData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getEpisodes(false) } doReturn object :
                LiveData<Result<List<Media>>>() {
                init {
                    value = Result.error("error")
                }
            }
        }
        useCase = UseCaseImpl(repository)
        val result = useCase.getEpisodes()
        result.observeForever { }
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR && LiveDataTestUtil.getValue(
                result
            ).message == "error"
        )

    }

    //


    @Test
    fun testChannelsLoadingData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getChannels(false) } doReturn object :
                LiveData<Result<List<Channel>>>() {
                init {
                    value = Result.loading()
                }
            }
        }
        useCase = UseCaseImpl(repository)

        val result = useCase.getChannels()
        result.observeForever { }
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)

    }


    @Test
    fun testChannelsSuccessData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getChannels(false) } doReturn object :
                LiveData<Result<List<Channel>>>() {
                init {
                    value = Result.success(TestUtils().fakeChannels)
                }
            }
        }
        useCase = UseCaseImpl(repository)

        val result = useCase.getChannels()

        result.observeForever { }

        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS && LiveDataTestUtil.getValue(
                result
            ).data == TestUtils().fakeChannels
        )

    }

    @Test
    fun testChannelssErrorData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getChannels(false) } doReturn object :
                LiveData<Result<List<Channel>>>() {
                init {
                    value = Result.error("error")
                }
            }
        }
        useCase = UseCaseImpl(repository)
        val result = useCase.getChannels()
        result.observeForever { }
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR && LiveDataTestUtil.getValue(
                result
            ).message == "error"
        )
    }

    // Categories


    @Test
    fun testCategoriesLoadingData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getCategories(false) } doReturn object :
                LiveData<Result<List<Category>>>() {
                init {
                    value = Result.loading()
                }
            }
        }
        useCase = UseCaseImpl(repository)

        val result = useCase.getCategories()
        result.observeForever { }
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)

    }


    @Test
    fun testCategoriesSuccessData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getCategories(false) } doReturn object :
                LiveData<Result<List<Category>>>() {
                init {
                    value = Result.success(TestUtils().fakeCategories)
                }
            }
        }
        useCase = UseCaseImpl(repository)

        val result = useCase.getCategories()

        result.observeForever { }

        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS && LiveDataTestUtil.getValue(
                result
            ).data == TestUtils().fakeCategories
        )

    }

    @Test
    fun testCategoriesErrorData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getCategories(false) } doReturn object :
                LiveData<Result<List<Category>>>() {
                init {
                    value = Result.error("error")
                }
            }
        }
        useCase = UseCaseImpl(repository)
        val result = useCase.getCategories()
        result.observeForever { }
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR && LiveDataTestUtil.getValue(
                result
            ).message == "error"
        )
    }


}