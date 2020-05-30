package com.vinay.doubtnut.remote.repository

import com.vinay.doubtnut.remote.ApiService
import com.vinay.doubtnut.remote.model.ArticleResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by VINAY on 30/05/20.
 */
class NewsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    NewsRepository {

    override fun getNewsData(country: String, apiKey: String): Single<ArticleResponse> {
        return apiService.getNewsData(country, apiKey)
    }
}