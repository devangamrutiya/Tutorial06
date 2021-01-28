package com.rku_18fotca11001.tutorial06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class Welcome extends AppCompatActivity {
    TextView textView1;
   SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        preferences = getSharedPreferences("university",MODE_PRIVATE);
        String userPreferences = preferences.getString("username","");

        if (userPreferences.equals(""))
        {
            Intent intent = new Intent(Welcome.this,Login.class);
            startActivity(intent);
            finish();
        }
    }

    public void logout(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("username");
        editor.commit();

        Intent intent = new Intent(Welcome.this,Login.class);
        startActivity(intent);
        finish();
    }
}