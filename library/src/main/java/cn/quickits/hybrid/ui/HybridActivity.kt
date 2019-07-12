package cn.quickits.hybrid.ui

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
class HybridActivity : AppCompatActivity() {

    private lateinit var hybrid: Hybrid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hybrid)
        hybrid = Hybrid(this, web_view)

        hybrid.setupWebView()
        web_view.loadUrl("http://baidu.com")
    }

}