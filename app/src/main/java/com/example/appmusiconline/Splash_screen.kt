package com.example.appmusiconline

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.appmusiconline.databinding.ActivitySplashScreenBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

private lateinit var auth:FirebaseAuth
private lateinit var googleSignInClient: GoogleSignInClient
private lateinit var binding: ActivitySplashScreenBinding
class Splash_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        Init()
        setControlClick()
        val view = binding.root
        setContentView(view)
    }
    private fun setControlClick() {
        binding.btnLoginGoogle.setOnClickListener {
            SignupInGoogle()
        }
    }
    private fun Init() {
        auth= FirebaseAuth.getInstance()
        val gson=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient=GoogleSignIn.getClient(this,gson)
    }

    private fun SignupInGoogle() {
        val signInIntent= googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }
    private val launcher =registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        result->
        if(result.resultCode==Activity.RESULT_OK)
        {
            val task=GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResult(task)
        }
    }
    private fun handleResult(task: Task<GoogleSignInAccount>) {
         if (task.isSuccessful)
         {
            val account:GoogleSignInAccount=task.result
             if(account!=null)
             {
                 updateUI(account)
             }
         }
         else
         {
             Toast.makeText(this, "failed", Toast.LENGTH_LONG).show()

         }
    }
    private fun updateUI(account: GoogleSignInAccount) {
       val credential=GoogleAuthProvider.getCredential(account.idToken,null)
      auth.signInWithCredential(credential).addOnCompleteListener {
          if (it.isSuccessful) {
              val intent: Intent = Intent(this, MainActivity::class.java)
              intent.putExtra("Uid",account.id)
              intent.putExtra("NameAccount",account.displayName)
              startActivity(intent)
              finishAffinity()
          } else {
              Toast.makeText(this, "failed", Toast.LENGTH_LONG).show()
          }
      }
    }
}