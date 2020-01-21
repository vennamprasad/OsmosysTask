package com.osmosys.task.view

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.osmosys.task.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun signUp(view: View) {
        startActivity(Intent(this, SplashActivity::class.java))
    }

    fun skip(view: View) {
        startActivity(Intent(this, SplashActivity::class.java))
    }
}
