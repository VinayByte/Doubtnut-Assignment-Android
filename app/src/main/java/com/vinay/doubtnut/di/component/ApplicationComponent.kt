package com.vinay.doubtnut.di.component

import com.vinay.doubtnut.App
import com.vinay.doubtnut.di.module.ActivityBindingModule
import com.vinay.doubtnut.di.module.ApiModule
import com.vinay.doubtnut.di.module.ViewModelModule
import com.vinay.doubtnut.di.scope.AppScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by VINAY on 30/05/20.
 */
@AppScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class,
        ApiModule::class]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<App>
}