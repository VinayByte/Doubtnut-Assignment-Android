package com.vinay.doubtnut.di.module

import com.vinay.doubtnut.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by VINAY on 30/05/20.
 */
@Module
internal abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [FragmentProviderModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

}