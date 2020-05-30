package com.vinay.doubtnut.view.fragment

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinay.doubtnut.R
import com.vinay.doubtnut.callback.IFragmentChangeCallback
import com.vinay.doubtnut.remote.model.Article
import com.vinay.doubtnut.view.adapter.NewsListAdapter
import kotlinx.android.synthetic.main.article_layout.*

/**
 * Created by VINAY on 30/05/20.
 */
class ArticleFragment : AbstractArticleFragment() {

    companion object {
        val TAG = ArticleFragment::class.java.name
        fun newInstance(): ArticleFragment {
            return ArticleFragment()
        }
    }

    private lateinit var fragmentChangeListener: IFragmentChangeCallback
    private val newsData by lazy { ArrayList<Article>() }
    private lateinit var newsAdapter: NewsListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentChangeListener = context as IFragmentChangeCallback
    }

    override fun getLayoutRes() = R.layout.article_layout

    override fun viewInitialization(view: View) {
        super.viewInitialization(view)
        initAdapter()
    }

    private fun initAdapter() {
        newsAdapter = NewsListAdapter(newsData,
            handleNewsArticleClick = { article -> onArticleClick(article) })
        with(parent_recycler) {
            this.visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = newsAdapter
        }
    }

    override fun setIssuesData(list: List<Article>) {
        newsData.addAll(list)
        with(newsAdapter) {
            notifyDataSetChanged()
        }
    }

    override fun showLoadingState(loading: Boolean) {
        if (loading) {
            shimmer_view_container.visibility = View.VISIBLE
            shimmer_view_container.startShimmerAnimation()
        } else {
            shimmer_view_container.stopShimmerAnimation()
            shimmer_view_container.visibility = View.GONE
        }
    }

    override fun onError(message: String) {
        showToast(message)
    }


    private fun onArticleClick(article: Article) {
        fragmentChangeListener.onFragmentChange(
            NewsDetailFragment.getInstance(article),
            NewsDetailFragment.TAG
        )
    }

}