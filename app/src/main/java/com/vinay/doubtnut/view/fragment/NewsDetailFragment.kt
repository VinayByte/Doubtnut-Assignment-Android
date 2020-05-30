package com.vinay.doubtnut.view.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.vinay.doubtnut.R
import com.vinay.doubtnut.callback.IFragmentChangeCallback
import com.vinay.doubtnut.remote.model.Article
import kotlinx.android.synthetic.main.news_detail_layout.*

/**
 * Created by VINAY on 30/05/20.
 */
class NewsDetailFragment : AbstractNewsDetailFragment() {

    companion object {
        val TAG = NewsDetailFragment::class.java.name
        fun getInstance(article: Article): NewsDetailFragment {
            val fragment = NewsDetailFragment()
            val arg = Bundle()
            arg.putParcelable(NEWS_DETAIL_DATA, article)
            fragment.arguments = arg
            return fragment
        }
    }

    private lateinit var fragmentChangeListener: IFragmentChangeCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentChangeListener = context as IFragmentChangeCallback
    }

    override fun getLayoutRes() = R.layout.news_detail_layout
    override fun showLoadingState(loading: Boolean) {}
    override fun onError(message: String) {}

    override fun viewInitialization(view: View) {
        super.viewInitialization(view)
        setUIData(view)
    }

    private fun setUIData(view: View) {
        with(view) {
            getArticleData()?.let { article ->
                txt_article_title.text = article.title
                txt_article_desc.text = article.description
                txt_article_content.text = article.content
                Glide.with(context)
                    .load(article.urlToImage)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv_article)
                txt_article_more.setOnClickListener {
                    article.url?.let { link -> openNewsLink(link) }
                }
            }
        }
    }

    private fun openNewsLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}