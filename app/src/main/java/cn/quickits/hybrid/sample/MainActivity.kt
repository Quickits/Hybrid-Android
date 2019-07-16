package cn.quickits.hybrid.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.quickits.hybrid.HybridConfig
import cn.quickits.hybrid.ui.HybridActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HybridConfig.addCustomApi(AuthHandler())

        btn.setOnClickListener {
            HybridActivity.launch(this, "http://192.168.100.87:99/huodong/F81E0DFF8D0741C39F52C2A4295C7B0E")
//            HybridActivity.launch(this, "http://192.168.100.115:3000")
        }
    }

}
