package com.osmosys.task.view

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.osmosys.task.R
import com.osmosys.task.utils.Common.Companion.EMAIL_ADDRESS
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.util.regex.Pattern


class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        object : CountDownTimer(5000, 1000) {
            override fun onFinish() {
                bookITextView.visibility = View.GONE
                loadingProgressBar.visibility = View.GONE
                rootView.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.backgroundColor))
                bookIconImageView.setImageResource(R.drawable.exp_l_ico)
                startAnimation()
            }

            override fun onTick(p0: Long) {}
        }.start()

        ePassword.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                logIn(v)
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun startAnimation() {
        bookIconImageView.animate().apply {
            x(56f)
            y(56f)
            duration = 1000
        }.setListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                afterAnimationView.visibility = VISIBLE
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
            }
        })
    }

    fun signUp(view: View) {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    fun skip(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun logIn(view: View) {
        if(validate())
        startActivity(Intent(this, MainActivity::class.java))
    }
    fun validate(): Boolean {
        var valid = true
        val email: String = eloginId.text.toString()
        val password: String = ePassword.text.toString()
        if (email.isEmpty() || !EMAIL_ADDRESS.matcher(email).matches()) {
            eloginId.error = "enter a valid email address"
            valid = false
        } else {
            eloginId.error = null
        }
        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            ePassword.error = "between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            ePassword.error = null
        }
        return valid
    }
}
