package com.demo.mindvalley.main.data.remote.source

import com.demo.mindvalley.di.qualifiers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import com.demo.mindvalley.main.data.remote.services.MainService


class MainRemoteDataSourceImpl @Inject constructor(
    private val service: MainService,
    @IO private val context: CoroutineContext
) : MainRemoteDataSource {

}
