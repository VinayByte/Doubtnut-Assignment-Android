package com.vinay.doubtnut.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinay.doubtnut.R
import com.vinay.doubtnut.remote.model.Article
import kotlinx.android.synthetic.main.article_row_item.view.*

/**
 * Created by VINAY on 30/05/20.
 */
class NewsListAdapter(
    private val items: List<Article>,
    private val handleNewsArticleClick: (Article) -> Unit = {}
) :
    RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.article_row_item, parent, false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class NewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(article: Article) {
            with(view) {
                txt_article_title.text = article.title
                txt_article_desc.text = article.description
                Glide.with(itemView.context)
                    .load(article.urlToImage)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv_article)
            }

            view.setOnClickListener {
                handleNewsArticleClick(article)
            }
        }
    }
}