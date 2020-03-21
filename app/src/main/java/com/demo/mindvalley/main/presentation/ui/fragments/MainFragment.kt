package com.demo.mindvalley.main.presentation.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.demo.mindvalley.R
import com.demo.mindvalley.common.ViewModelFactory
import com.demo.mindvalley.main.presentation.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MainFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private lateinit var mMainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            try {
                mMainViewModel =
                    ViewModelProviders.of(activity!!, viewModelFactory)
                        .get(MainViewModel::class.java)

            } finally {

            }
        }
    }


}
