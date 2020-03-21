package com.demo.mindvalley.main.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.demo.mindvalley.main.domain.UseCase
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val mUseCase: UseCase
) : ViewModel() {


}