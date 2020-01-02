package com.kotlin.mvvm.structure.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.kotlin.mvvm.structure.R
import com.kotlin.mvvm.structure.base.BaseActivity
import com.kotlin.mvvm.structure.databinding.ActivityLoginBinding
import com.kotlin.mvvm.structure.ui.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.base_activity.*

class ActivityLogin : BaseActivity() {

    lateinit var activityLoginBinding: ActivityLoginBinding

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityLoginBinding = putContentView(R.layout.activity_login) as ActivityLoginBinding

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        activityLoginBinding.loginViewModel = loginViewModel

        initToolbar()
        base_activity_toolbar.visibility = View.GONE
        
    }

}