package com.vinay.doubtnut.remote

import com.vinay.doubtnut.remote.model.ArticleResponse
import com.vinay.doubtnut.remote.repository.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by VINAY on 30/05/20.
 */
interface GetNewsUseCase {
    fun getNewsData(country: String, apiKey: String): Single<ArticleResponse>
}

class GetNewsUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) :
    GetNewsUseCase {

    override fun getNewsData(country: String, apiKey: String): Single<ArticleResponse> =
        newsRepository.getNewsData(country, apiKey)

}