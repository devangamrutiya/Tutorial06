package com.rku_18fotca11001.tutorial06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username,password;
    Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.edtuser);
        password = findViewById(R.id.edtpassword);
        login = findViewById(R.id.btnlogin);
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= 26)
                {
                    vibrator.vibrate(VibrationEffect.createOneShot( 50, VibrationEffect.DEFAULT_AMPLITUDE));
                    // vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                }
                else
                {
                    vibrator.vibrate(50);
                }
                String valusername = username.getText().toString();
                String valpassword = password.getText().toString();
                if (valusername.equals(""))
                {
                    Toast.makeText(Login.this,"Username can not be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (valpassword.equals(""))
                {
                    Toast.makeText(Login.this,"Password can not be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (valpassword.length()< 4 )
                {
                    Toast.makeText(Login.this,"Password length should be 6",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(valusername).matches())
                {
                    Toast.makeText(Login.this,"Email-Address can't be identify",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(valusername.equals("a@gmail.com")&& valpassword.equals("admin"))
                {
                    SharedPreferences preferences = getSharedPreferences("university",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username",valusername);
                    editor.commit();
                    Intent intent = new Intent(Login.this, Welcome.class);
                    intent.putExtra("wel",valusername);
                    startActivity(intent);
                    finish();
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();


                }else
                {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
                 public void txtregistration(View view) {
                  startActivity(new Intent(Login.this,Registration.class));
                 }

}