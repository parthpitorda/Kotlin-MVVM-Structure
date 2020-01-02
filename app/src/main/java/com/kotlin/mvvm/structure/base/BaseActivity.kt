package com.kotlin.mvvm.structure.base

import android.app.Application
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.kotlin.mvvm.structure.R
import com.kotlin.mvvm.structure.databinding.BaseActivityBinding
import com.kotlin.mvvm.structure.utils.listener.ConnectivityReceiver
import com.trust_tag.utils.AppPermissions
import com.trust_tag.utils.AppPreferences
import com.trust_tag.utils.AppUtils
import javax.inject.Inject

open class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    lateinit var context: Application

    @Inject
    lateinit var appUtils: AppUtils

    @Inject
    lateinit var appPreferences: AppPreferences

    private lateinit var baseActivityBinding: BaseActivityBinding

    lateinit var appPermissions: AppPermissions

    lateinit var baseViewModel: BaseViewModel

    private var mSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                statusBarColor = Color.TRANSPARENT
            }
        }

        MyApplication.appComponent.inject(this)

        baseActivityBinding = DataBindingUtil.setContentView(this, R.layout.base_activity)
        baseViewModel = ViewModelProviders.of(this).get(BaseViewModel::class.java)

        appPermissions = AppPermissions(this)

        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        appUtils.printLog()
        appPreferences.printLog()

    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.base_activity_toolbar)
    }

    protected open fun <T : ViewDataBinding?> putContentView(@LayoutRes resId: Int): T {
        val frameLayout = findViewById<ViewGroup?>(R.id.base_activity_fl_content)

        return DataBindingUtil.inflate<T>(
            layoutInflater,
            resId,
            frameLayout,
            true
        )
    }

    fun showMessage(isConnected: Boolean) {
        if (!isConnected) {
            val messageToUser = "You are offline now." //TODO
            mSnackBar = Snackbar.make(
                findViewById(R.id.base_activity_drawer_container),
                messageToUser,
                Snackbar.LENGTH_LONG
            ) //Assume "rootLayout" as the root layout of every activity.
            mSnackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            mSnackBar?.show()
        } else {
            val messageToUser = "You are online now." //TODO
            mSnackBar = Snackbar.make(
                findViewById(R.id.base_activity_drawer_container),
                messageToUser,
                Snackbar.LENGTH_LONG
            ) //Assume "rootLayout" as the root layout of every activity.
            mSnackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            mSnackBar?.show()
        }
    }

    fun showMessage(strError: String) {
        mSnackBar = Snackbar.make(
            findViewById(R.id.base_activity_drawer_container),
            strError,
            Snackbar.LENGTH_LONG
        ) //Assume "rootLayout" as the root layout of every activity.
        mSnackBar?.duration = BaseTransientBottomBar.LENGTH_LONG
        mSnackBar?.show()
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        MyApplication.isInternetAvailable = isConnected
    }

}