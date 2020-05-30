package com.vinay.doubtnut.view.fragment

import android.view.View
import com.vinay.doubtnut.base.BaseFragment
import com.vinay.doubtnut.remote.model.Article

/**
 * Created by VINAY on 30/05/20.
 */
abstract class AbstractNewsDetailFragment : BaseFragment() {

    companion object {
        val TAG = AbstractNewsDetailFragment::class.java.name
        const val NEWS_DETAIL_DATA = "news_detail_data"
    }

    protected fun getArticleData() = arguments?.getParcelable<Article>(NEWS_DETAIL_DATA)
    override fun viewInitialization(view: View) {}
}