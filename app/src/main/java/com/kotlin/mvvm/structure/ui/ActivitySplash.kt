package com.kotlin.mvvm.structure.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.kotlin.mvvm.structure.R
import com.kotlin.mvvm.structure.base.BaseActivity
import com.kotlin.mvvm.structure.databinding.ActivitySplashBinding
import kotlinx.android.synthetic.main.base_activity.*

class ActivitySplash : BaseActivity() {

    lateinit var activitySplashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySplashBinding = putContentView(R.layout.activity_splash) as ActivitySplashBinding

        initToolbar()
        base_activity_toolbar.visibility = View.GONE

        Handler().postDelayed({
            val i = Intent(this@ActivitySplash, ActivityLogin::class.java)
            finishAffinity()
            startActivity(i)
        }, 2000)


    }

}