package cn.quickits.hybrid.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.quickits.hybrid.Hybrid
import cn.quickits.hybrid.R
import kotlinx.android.synthetic.main.activity_hybrid.*


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-12 10:58
 **/
open class HybridActivity : AppCompatActivity() {

    private lateinit var hybrid: Hybrid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hybrid)
        title = ""

        hybrid = Hybrid(this, web_view)
        hybrid.setupWebView()

        lifecycle.addObserver(hybrid)

        intent?.getStringExtra(PARAM_PATH)?.let {
            web_view.loadUrl(it)
        }

    }

    override fun onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {

        private const val PARAM_PATH = "param_path"

        fun launch(context: Context, path: String) {
            context.startActivity(Intent(context, HybridActivity::class.java).apply {
                putExtra(PARAM_PATH, path)
            })
        }

    }

}