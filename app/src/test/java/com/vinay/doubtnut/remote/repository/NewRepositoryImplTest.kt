package com.vinay.doubtnut.remote.repository

import com.vinay.doubtnut.remote.ApiService
import com.vinay.doubtnut.remote.model.ArticleResponse
import com.vinay.doubtnut.testutil.Rx2SchedulersOverrideRule
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody


@RunWith(MockitoJUnitRunner::class)
class NewRepositoryImplTest {

    @JvmField
    @Rule
    val rx2SchedulersOverrideRule = Rx2SchedulersOverrideRule()

    private lateinit var repo: NewsRepository

    @Mock
    private lateinit var endpoint: ApiService

    @Before
    fun setUp() {
        repo = NewsRepositoryImpl(endpoint)
    }

    @Test
    fun `should return article when fetching new data`() {
        val expectedResponse = mock(ArticleResponse::class.java)
        `when`(endpoint.getNewsData("", "")).thenReturn(Single.create {
            it.onSuccess(expectedResponse)
        })
        endpoint.getNewsData("", "")
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(expectedResponse)
    }


    @Test
    fun `should return error when fetching new articles`() {
        val body = Response.error<ArticleResponse>(
            400,
            "{\"key\":[\"something went wrong\"]}".toResponseBody("application/json".toMediaTypeOrNull())
        )
        val exception = HttpException(body)

        `when`(endpoint.getNewsData("", "")).thenReturn(Single.error(exception))
        repo.getNewsData("", "")
            .test()
            .assertValueCount(0)
            .assertError { it is HttpException }

        verify(endpoint).getNewsData("", "")

    }

}