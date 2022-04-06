package com.example.supreet

import android.app.Dialog
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.example.supreet.databinding.ActivityQueryBinding
import com.example.supreet.databinding.DialogBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class query : AppCompatActivity() {
    lateinit var binding: ActivityQueryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query)
        val db = Firebase.firestore
        val user = hashMapOf(
            "first" to "Savi",
            "last" to "lastname",
            "class" to "Btech",
            "Rollno" to "180"

        )
//        binding.btnaddfloat.setOnClickListener {
//            dialog()
//        }

        db.collection("documents").document("details of student")
            .set(user)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        db.collection("documents").document("details of student")
            .update(
                mapOf(
                    "first" to "supreet",
                    "last" to "kaur"
                )
            )
//        db.collection("documents").document("details of student").delete().addOnSuccessListener{
//            Log.d(TAG, "DocumentSnapshot successfully deleted!") }
//            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
        //    Deleting collections from an Android client is not recommended.


    }

//    private fun dialog() {
//        var dialog = Dialog(this)
//        var dialogBinding = DialogBinding.inflate(layoutInflater)
//        dialog.setContentView(dialogBinding.root)
//        dialog.setCancelable(true)
//        dialog.window?.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//    }
}


