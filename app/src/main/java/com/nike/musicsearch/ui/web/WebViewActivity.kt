package com.nike.musicsearch.ui.web

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.nike.musicsearch.R
import com.nike.musicsearch.R.layout.activity_main
import com.nike.musicsearch.base.BaseActivity
import com.nike.musicsearch.base.BaseFragment


class WebViewActivity : BaseActivity(true) {

    private val webViewFragment = WebViewFragment()

    override fun fragment(): BaseFragment = webViewFragment

    @LayoutRes
    override fun getLayoutResId() = activity_main

    companion object {

        const val KEY_URL = "key_url"
        fun createIntent(activity: Activity, url: String): Intent {

            val intent = Intent(activity, WebViewActivity::class.java)
            val bundle = Bundle()
            bundle.putString(KEY_URL, url)
            intent.putExtras(bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.webview_activity_title)
        intent.extras?.getString(KEY_URL, "google.com")?.let { webViewFragment.setUrl(it) }
    }
}