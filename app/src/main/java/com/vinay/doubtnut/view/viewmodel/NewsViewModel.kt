package com.vinay.doubtnut.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinay.doubtnut.common.Event
import com.vinay.doubtnut.remote.GetNewsUseCase
import com.vinay.doubtnut.remote.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by VINAY on 30/05/20.
 */
class NewsViewModel @Inject constructor(private val useCase: GetNewsUseCase) :
    ViewModel() {

    private val _articleLiveData by lazy { MutableLiveData<Event<List<Article>>>() }
    val articleLiveData: LiveData<Event<List<Article>>> by lazy { _articleLiveData }


    var loadingState = MutableLiveData<Boolean>()

    private val _apiError by lazy { MutableLiveData<Event<Throwable>>() }
    val apiError: LiveData<Event<Throwable>> by lazy { _apiError }

    private val disposable by lazy { CompositeDisposable() }


    fun fetchNewsData(country: String, apiKey: String) {
        val issueDisposable = useCase.getNewsData(country, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingState.value = true }
            .doOnEvent { _, _ -> loadingState.value = false }
            .doOnError { loadingState.value = false }
            .subscribe(
                { it.articles?.let { list -> Event(list).run(_articleLiveData::postValue) } }
                ,
                { Event(it).run(_apiError::postValue) }
            )
        disposable.add(issueDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}