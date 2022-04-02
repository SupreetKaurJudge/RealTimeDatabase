package com.example.supreet

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.example.supreet.databinding.ActivityMain2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class MainActivity2 : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    val database = Firebase.database
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
      //  auth = firebase.auth

        binding.radioGroup.setOnCheckedChangeListener() { group, checkedId ->
            if (checkedId == R.id.maleradioButton) {
                Toast.makeText(this, binding.maleradioButton.text.toString(), Toast.LENGTH_SHORT).show()
            }
            if (checkedId == R.id.femaleradioButton) {
                Toast.makeText(this, binding.femaleradioButton.text.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        binding.tieName2.doOnTextChanged { text, start, before, count ->
            if (!TextUtils.isEmpty(text)) {
                binding.tieName2.error = null
            } else if (TextUtils.isEmpty(text)) {
                binding.tieName2.error = resources.getString(R.string.enter_username)
            }
        }

        binding.tieEmail2.doOnTextChanged { text, start, before, count ->
            if (!TextUtils.isEmpty(text)) {
                binding.tieEmail2.error = null
            } else if (TextUtils.isEmpty(text)) {

                binding.tieEmail2.error = resources.getString(R.string.enter_email)
            }
        }
        binding.tiepass2.doOnTextChanged { text, start, before, count ->
            if (!TextUtils.isEmpty(text)) {
                binding.tiepass2.error = null
            } else if (TextUtils.isEmpty(text)) {

                binding.tiepass2.error = resources.getString(R.string.enter_password)
            }
        }
        binding.tiePhno.doOnTextChanged { text, start, before, count ->
            if (!TextUtils.isEmpty(text)) {
                binding.tiePhno.error = null
            } else if (TextUtils.isEmpty(text)) {

                binding.tiePhno.error = resources.getString(R.string.enter_phonenumber)
            }
        }
        binding.tiecpass.doOnTextChanged { text, start, before, count ->
            if (!TextUtils.isEmpty(text)) {
                binding.tiecpass.error = null
            } else if (TextUtils.isEmpty(text)) {

                binding.tiecpass.error = resources.getString(R.string.enter_valid_password)
            }
        }



        binding.btnRegister.setOnClickListener {


            if (TextUtils.isEmpty(binding.tieName2.text.toString())) {
                //  Toast.makeText(this@MainActivity3, "enter full name", Toast.LENGTH_LONG).show
                binding.tieName2.error = resources.getString(R.string.enter_username)
            } else if (TextUtils.isEmpty(binding.tieEmail2.text.toString())) {
                // Toast.makeText(this@MainActivity3, "enter email", Toast.LENGTH_LONG).show()
                binding.tieEmail2.error = resources.getString(R.string.enter_email)
            } else if (!Pattern.matches(
                    Patterns.EMAIL_ADDRESS.toString(),
                    binding.tieEmail2.text.toString()
                )
            ) {
                //Toast.makeText(this@MainActivity3, "enter valid email", Toast.LENGTH_LONG).show()

                binding.tieEmail2.error = resources.getString(R.string.enter_valid_email)
            } else if (TextUtils.isEmpty(binding.tiepass2.text.toString())) {
                // Toast.makeText(this@MainActivity3, resources.getString(R.string.enter_password), Toast.LENGTH_LONG).show()
                binding.tiepass2.error = resources.getString(R.string.enter_password)

            } else if (binding.tiepass2.text.toString().length < 6) {
                // Toast.makeText(this@MainActivity3, "password should be of 6 digit length", Toast.LENGTH_LONG).show()
                binding.tiepass2.error = resources.getString(R.string.short_password)
            } else if (!binding.tiecpass.text.toString().equals(binding.tiepass2.text.toString())) {
                //  Toast.makeText(this@MainActivity3, "password and confirm password should match", Toast.LENGTH_LONG).show()
                binding.tiecpass.error = resources.getString(R.string.password_not_matched)

            } else if (binding.tiePhno.text.toString().length < 10) {
                //Toast.makeText(this@MainActivity3, "mobile number should not be less than 10 digits", Toast.LENGTH_LONG).show()
                binding.tiePhno.error = resources.getString(R.string.invalid_phonenumber)

            } else if (binding.radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(
                    this@MainActivity2,
                    "Select Gender",
                    Toast.LENGTH_LONG
                ).show()
            } else if (!binding.checkBox.isChecked) {
                Toast.makeText(
                    this@MainActivity2,
                    "Accept terms",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                createAccount(binding.tieEmail2.text.toString(),binding.tiepass2.text.toString())

            }

        }
    }



    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateRealTimeDatabase()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


    private fun updateRealTimeDatabase() {
        val user = auth.currentUser
        var myRef: DatabaseReference = database.getReference(user!!.uid)

        myRef.setValue(user!!.email)
        startActivity(Intent(this@MainActivity2, ProfileActivity::class.java))
        this.finish()
    }
}