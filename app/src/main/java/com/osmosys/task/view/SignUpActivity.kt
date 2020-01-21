package com.osmosys.task.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.osmosys.task.R
import com.osmosys.task.utils.Common.Companion.EMAIL_ADDRESS
import com.osmosys.task.utils.Common.Companion.PASSWORD_PATTERN
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    val values = arrayOf(
        "Vijayawada",
        "Jammu",
        "Dispur",
        "Patna",
        "Chandigarh",
        "Raypur",
        "Daman",
        "Delhi",
        "Panaji",
        "Gandhi Nagar",
        "Ranchi",
        "Bengaluru",
        "Thripura",
        "Kawaratti",
        "Bhopal",
        "Mumbai",
        "Impal",
        "Shillag",
        "Izwal",
        "Nagaland",
        "Hyderabad",
        "Agarthala"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            values
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sp_city.adapter = it
        }
        rg_type.setOnCheckedChangeListener(this)
    }

    fun submit(view: View) {
        if (validate())
            onBackPressed()

    }

    fun validate(): Boolean {
        var valid = true
        val fName: String = et_fName.text.toString()
        val lName: String = et_lName.text.toString()
        val email: String = et_eMail.text.toString()
        val password: String = et_Password.text.toString()
        val cnfPassword: String = et_cnfmPassword.text.toString()
        val position: Int = sp_city.selectedItemPosition
        val rg_type_id: Int = rg_type.checkedRadioButtonId
        val radioButton: RadioButton = findViewById(rg_type_id)
        val value: String = radioButton.text.toString().trim()
        if (fName.isEmpty()) {
            et_fName.error = "enter First Name"
            valid = false
        } else {
            et_fName.error = null
        }
        if (lName.isEmpty()) {
            et_lName.error = "enter Last Name"
            valid = false
        } else {
            et_lName.error = null
        }
        if (email.isEmpty() || !EMAIL_ADDRESS.matcher(email).matches()) {
            et_eMail.error = "enter a valid email address"
            valid = false
        } else {
            et_eMail.error = null
        }
        if (password.isEmpty()) {
            et_Password.error = "enter Password"
            valid = false
        } else if(!password_validate(password)) {
            et_Password.error = "password must contain lowercase uppercase chars and special symbols like @#\$% and length at least 8 characters and maximum of 12"
            valid = false
        }else{
          et_Password.error=null
        }
        if(!cnfPassword.equals(password)){
            et_cnfmPassword.error = "Password not Matched"
            valid = false
        }else{
            et_cnfmPassword.error=null
        }
        return valid
    }

    fun password_validate(password: String): Boolean {
        return PASSWORD_PATTERN.matcher(password).matches()
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {

    }
}
