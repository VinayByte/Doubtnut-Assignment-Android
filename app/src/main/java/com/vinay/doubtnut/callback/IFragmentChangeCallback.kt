package com.vinay.doubtnut.callback

import androidx.fragment.app.Fragment

/**
 * Created by VINAY on 30/05/20.
 */
interface IFragmentChangeCallback {
    fun onFragmentChange(fragment: Fragment, tag: String)
}