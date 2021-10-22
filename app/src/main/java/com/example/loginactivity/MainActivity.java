package com.example.loginactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText Username, Password;
    private Button Login;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkLogin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        Login = findViewById(R.id.login);
        // Login OnClick
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Username.getText().toString().equals("user") && Password.getText().toString().equals("user")) {
                    // Save Username
                    mEditor.putString(getString(R.string.username), Username.getText().toString());
                    mEditor.commit();

                    // Save Has Login
                    mEditor.putString(getString(R.string.login), "true");
                    mEditor.commit();

                    Intent home = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(home);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Login gagal", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkLogin() {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String hasLogin = mPreferences.getString(getString(R.string.login), "false");

        if(hasLogin.equals("true")) {
            Intent home = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(home);
            finish();
        }
    }
}