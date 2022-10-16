package com.volkankelleci.photosharingproject

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

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

        }
}
    fun sign (view: View){
        val email=emailText.text.toString()
        val password=passwordText.text.toString()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                val updatedUser=auth.currentUser
                Toast.makeText(this,"DONE:${updatedUser}",Toast.LENGTH_LONG)




                val intent=Intent(this,Photos_Activitiy::class.java)
                startActivity(intent)
                finish()
            }else {

            }
        }
    }
    fun save(view: View){
        val email=emailText.text.toString()
        val password=passwordText.text.toString()
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                val intent=Intent(this,Photos_Activitiy::class.java)
                startActivity(intent)
                finish()
            }else{

            }
        }
    }

    }




