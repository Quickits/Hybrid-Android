package cn.quickits.hybrid.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.quickits.hybrid.ui.HybridActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            HybridActivity.launch(this, "http://192.168.100.115:8082")
        }
    }
}
