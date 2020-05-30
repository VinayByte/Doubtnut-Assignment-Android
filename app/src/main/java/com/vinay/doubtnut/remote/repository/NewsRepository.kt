package com.vinay.doubtnut.remote.repository

import com.vinay.doubtnut.remote.model.ArticleResponse
import io.reactivex.Single

/**
 * Created by VINAY on 30/05/20.
 */
interface NewsRepository {
    fun getNewsData(country: String, apiKey: String): Single<ArticleResponse>
}