package com.demo.mindvalley.main.domain

import com.demo.mindvalley.main.data.repository.MainRepository
import javax.inject.Inject

class UseCaseImpl @Inject constructor(private val mMainRepository: MainRepository) : UseCase {

}
