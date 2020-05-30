package com.vinay.doubtnut.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vinay.doubtnut.base.BaseFragment
import com.vinay.doubtnut.base.ViewModelFactory
import com.vinay.doubtnut.common.EventObserver
import com.vinay.doubtnut.remote.model.Article
import com.vinay.doubtnut.view.viewmodel.NewsViewModel
import javax.inject.Inject

/**
 * Created by VINAY on 30/05/20.
 */
abstract class AbstractArticleFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchNewsData("us", "3f4be01eab73409c91ab094dda6ab669")
    }

    override fun viewInitialization(view: View) {
        observeDataChange()
    }


    private fun observeDataChange() {
        viewModel.loadingState.observe(viewLifecycleOwner, Observer { showLoadingState(it) })
        viewModel.apiError.observe(viewLifecycleOwner, EventObserver { handleError(it) })
        viewModel.articleLiveData.observe(viewLifecycleOwner, EventObserver {
            setIssuesData(it)
        })
    }


    abstract fun setIssuesData(list: List<Article>)

}