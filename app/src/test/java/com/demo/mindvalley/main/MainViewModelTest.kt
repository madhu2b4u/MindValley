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
import com.demo.mindvalley.main.domain.UseCase
import com.demo.mindvalley.main.presentation.viewmodel.MainViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MainViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    lateinit var viewModel: MainViewModel
    lateinit var useCase: UseCase

    @Before
    fun init() {
        //empty
    }

    @Test
    fun testCategoryLoadingData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getCategories(false) } doReturn object :
                LiveData<Result<List<Category>>>() {
                init {
                    value = Result.loading()
                }
            }
        }

        viewModel = MainViewModel(useCase)
        val result = viewModel.categoryResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(LiveDataTestUtil.getValue(viewModel.categoryResult).status == Status.LOADING)

    }

    @Test
    fun testCategorySuccessData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getCategories(false) } doReturn object :
                LiveData<Result<List<Category>>>() {
                init {
                    value = Result.success(TestUtils().fakeCategories)
                }
            }
        }

        viewModel = MainViewModel(useCase)
        val result = viewModel.categoryResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).data == TestUtils().fakeCategories
        )
    }

    @Test
    fun testCategoryErrorData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getCategories(false) } doReturn object :
                LiveData<Result<List<Category>>>() {
                init {
                    value = Result.error("error")
                }
            }
        }

        viewModel = MainViewModel(useCase)
        val result = viewModel.categoryResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR &&
                    LiveDataTestUtil.getValue(result).message == "error"
        )
    }

    @Test
    fun testCategoryFetchDataData() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { getCategories(true) } doReturn object :
                LiveData<Result<List<Category>>>() {
                init {
                    value = Result.error("error")
                }
            }

            onBlocking { getCategories(false) } doReturn object :
                LiveData<Result<List<Category>>>() {
                init {
                    value = Result.success(TestUtils().fakeCategories)
                }
            }
        }

        viewModel = MainViewModel(useCase)

        val result = viewModel.categoryResult
        result.observeForever {}
        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).data == TestUtils().fakeCategories
        )

        kotlinx.coroutines.delay(2000)
        viewModel.loadCategories(true)
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR &&
                    LiveDataTestUtil.getValue(result).message == "error"
        )
    }


    //Episodes

    @Test
    fun testEpisodesLoadingData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getEpisodes(false) } doReturn object :
                LiveData<Result<List<Media>>>() {
                init {
                    value = Result.loading()
                }
            }
        }

        viewModel = MainViewModel(useCase)
        val result = viewModel.episodeResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(LiveDataTestUtil.getValue(viewModel.episodeResult).status == Status.LOADING)

    }

    @Test
    fun testEpisodesSuccessData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getEpisodes(false) } doReturn object :
                LiveData<Result<List<Media>>>() {
                init {
                    value = Result.success(TestUtils().fakeMedias)
                }
            }
        }

        viewModel = MainViewModel(useCase)
        val result = viewModel.episodeResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).data == TestUtils().fakeMedias
        )
    }

    @Test
    fun testEpisodeErrorData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getEpisodes(false) } doReturn object :
                LiveData<Result<List<Media>>>() {
                init {
                    value = Result.error("error")
                }
            }
        }

        viewModel = MainViewModel(useCase)
        val result = viewModel.episodeResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR &&
                    LiveDataTestUtil.getValue(result).message == "error"
        )
    }

    @Test
    fun testEpisodeFetchDataData() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { getEpisodes(true) } doReturn object :
                LiveData<Result<List<Media>>>() {
                init {
                    value = Result.error("error")
                }
            }
            onBlocking { getEpisodes(false) } doReturn object :
                LiveData<Result<List<Media>>>() {
                init {
                    value = Result.success(TestUtils().fakeMedias)
                }
            }
        }

        viewModel = MainViewModel(useCase)
        val result = viewModel.episodeResult
        result.observeForever {}
        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).data == TestUtils().fakeMedias
        )
        kotlinx.coroutines.delay(2000)
        viewModel.loadEpisodes(true)
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR &&
                    LiveDataTestUtil.getValue(result).message == "error"
        )
    }

    //Channels

    @Test
    fun testChannelsLoadingData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getChannels(false) } doReturn object :
                LiveData<Result<List<Channel>>>() {
                init {
                    value = Result.loading()
                }
            }
        }

        viewModel = MainViewModel(useCase)
        val result = viewModel.channelResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(LiveDataTestUtil.getValue(viewModel.channelResult).status == Status.LOADING)

    }

    @Test
    fun testChannelsSuccessData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getChannels(false) } doReturn object :
                LiveData<Result<List<Channel>>>() {
                init {
                    value = Result.success(TestUtils().fakeChannels)
                }
            }
        }

        viewModel = MainViewModel(useCase)
        val result = viewModel.channelResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).data == TestUtils().fakeChannels
        )
    }

    @Test
    fun testChanelsErrorData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getChannels(false) } doReturn object :
                LiveData<Result<List<Channel>>>() {
                init {
                    value = Result.error("error")
                }
            }
        }

        viewModel = MainViewModel(useCase)
        val result = viewModel.channelResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR &&
                    LiveDataTestUtil.getValue(result).message == "error"
        )
    }

    @Test
    fun testChannelsFetchDataData() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { getChannels(true) } doReturn object :
                LiveData<Result<List<Channel>>>() {
                init {
                    value = Result.error("error")
                }
            }
            onBlocking { getChannels(false) } doReturn object :
                LiveData<Result<List<Channel>>>() {
                init {
                    value = Result.success(TestUtils().fakeChannels)
                }
            }
        }

        viewModel = MainViewModel(useCase)
        val result = viewModel.channelResult
        result.observeForever {}
        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).data == TestUtils().fakeChannels
        )
        kotlinx.coroutines.delay(2000)
        viewModel.loadChannels(true)
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR &&
                    LiveDataTestUtil.getValue(result).message == "error"
        )
    }


}