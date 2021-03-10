package com.nike.musicsearch.ui.songs


import androidx.annotation.LayoutRes
import com.nike.musicsearch.R
import com.nike.musicsearch.base.BaseActivity
import com.nike.musicsearch.base.BaseFragment

class SongsActivity : BaseActivity(false) {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main_songs

    override fun fragment(): BaseFragment = SongsFragment()
}
