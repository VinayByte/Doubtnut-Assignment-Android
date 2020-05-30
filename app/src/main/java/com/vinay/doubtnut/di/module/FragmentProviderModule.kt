package com.vinay.doubtnut.di.module

import com.vinay.doubtnut.view.fragment.ArticleFragment
import com.vinay.doubtnut.view.fragment.NewsDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by VINAY on 30/05/20.
 */
@Module
abstract class FragmentProviderModule {

    @ContributesAndroidInjector
    abstract fun provideIssueFragment(): ArticleFragment

    @ContributesAndroidInjector
    abstract fun provideCommentFragment(): NewsDetailFragment

}