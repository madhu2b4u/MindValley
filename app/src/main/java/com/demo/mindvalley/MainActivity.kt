package com.demo.mindvalley

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.demo.mindvalley.common.Utils
import com.demo.mindvalley.main.presentation.ui.activity.HomeActivity
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utils().transparentStatusAndNavigation(window)

        //Navigate to Main Screen after 5 seconds
        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 5000)
    }


}
