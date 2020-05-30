package com.vinay.doubtnut.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vinay.doubtnut.R
import com.vinay.doubtnut.base.BaseActivity
import com.vinay.doubtnut.callback.IFragmentChangeCallback
import com.vinay.doubtnut.view.fragment.ArticleFragment

/**
 * Created by VINAY on 30/05/20.
 */
class MainActivity : BaseActivity(), IFragmentChangeCallback {

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openArticleFragment()
    }

    private fun openArticleFragment() {
        replaceFragment(R.id.container, ArticleFragment.newInstance(), ArticleFragment.TAG)
    }

    override fun onFragmentChange(fragment: Fragment, tag: String) {
        replaceFragment(R.id.container, fragment, tag, true)
    }
}
