package com.priyank.sample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.priyank.snacyalert.SnacyAlert
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonShow.setOnClickListener {
            SnacyAlert.create(this)
                .setTitle("Title")
                .setText("Text...")
                .setBackgroundColorInt(Color.GREEN)
                .setIcon(R.drawable.ic_notifications_black_24dp)
                .setDuration(2000)
                .showIcon(true)
                .show()
        }
    }
}
