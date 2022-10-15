package com.volkankelleci.photosharingproject

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UsersActivitiy : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
}

    fun sign (email:String,password:String,view: View){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Log.d(TAG, "signInWithEmail:success")
                val user = auth.currentUser
                updateUI(user)
            }else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", it.exception)
                Toast.makeText(baseContext, "Authentication failed.",Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }
    fun save(view: View,email:String,password:String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            if (it.isSuccessful){
                Log.d(TAG,"newUsersCreated:success")
                val user=auth.currentUser
                updateUI(user)
            }else{
                Log.w(TAG,"newUsersCreates:failure",it.exception)
                Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }
    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                // Email Verification sent
            }
        // [END send_email_verification]
    }


    private fun reload() {

    }
    fun updateUI(user: FirebaseUser?) {

    }
    companion object{
        private const val TAG = "EmailPassword"
    }
}


