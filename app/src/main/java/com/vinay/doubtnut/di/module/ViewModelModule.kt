package com.vinay.doubtnut.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinay.doubtnut.base.ViewModelFactory
import com.vinay.doubtnut.di.scope.ViewModelKey
import com.vinay.doubtnut.view.viewmodel.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by VINAY on 30/05/20.
 */
@Module
internal abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindMainViewModel(viewModel: NewsViewModel): ViewModel
}