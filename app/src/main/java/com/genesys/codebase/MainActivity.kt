package com.genesys.codebase

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.genesys.codebase.databinding.ActivityMainBinding
import com.genesys.core.common.base.BaseActivity
import com.genesys.feature.template.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLazyViewBinding(): Lazy<ActivityMainBinding> = lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun statusBarInsetTargets(): List<View> = listOf(viewBinding.toolbar)

    override fun initViews(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment())
                .commit()
        }
    }
}
