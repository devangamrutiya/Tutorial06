package com.rku_18fotca11001.tutorial06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {
    TextView username;
    Menu logout;

   SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        logout = findViewById(R.id.logout);

        preferences = getSharedPreferences("university",MODE_PRIVATE);
        String userPreferences = preferences.getString("username","");

        if (userPreferences.equals(""))
        {
            Intent intent = new Intent(Welcome.this,Login.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:

                AlertDialog.Builder builder = new AlertDialog.Builder(Welcome.this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you Sure");


                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("username");
                        editor.commit();

                        Intent intent = new Intent(Welcome.this,Login.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Welcome.this, "Logout Successful", Toast.LENGTH_SHORT).show();


//                Toast.makeText(Welcome.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(View view) {
  }
}
