package com.vinay.doubtnut.remote

import com.vinay.doubtnut.remote.model.ArticleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by VINAY on 30/05/20.
 */
interface ApiService {

    @GET("top-headlines")
    fun getNewsData(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Single<ArticleResponse>

}