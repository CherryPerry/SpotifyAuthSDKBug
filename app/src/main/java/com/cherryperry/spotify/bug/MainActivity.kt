package com.cherryperry.spotify.bug

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class MainActivity : AppCompatActivity() {

    private lateinit var login: Button
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result = findViewById(R.id.result)
        login = findViewById(R.id.login)
        login.setOnClickListener {
            // API 23-26 – BUG
            // API 27+ – OK
            val intent = AuthorizationClient.createLoginActivityIntent(
                this,
                AuthorizationRequest
                    .Builder(
                       TODO("Token"),
                        AuthorizationResponse.Type.TOKEN,
                        TODO("Callback"),
                    )
                    .build()
            )
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthorizationClient.getResponse(resultCode, data)
        result.text = response.type.name
    }

}