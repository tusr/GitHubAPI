package com.example.tusharmahale.githubapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button infoButton;
EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.back)));
        infoButton = findViewById(R.id.infoButton);
        userName = findViewById(R.id.username);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent userActivity = new Intent(getApplicationContext(),UserActivity.class);
                userActivity.putExtra("userName",userName.getText().toString());
              if(!isOnline()){
                  Toast.makeText(getApplicationContext(),"Check Internet Connection",Toast.LENGTH_SHORT).show();
              }
               else if(isOnline()){
                if(userName.getText().toString().trim().length()<4)
                {
                    Toast.makeText(getApplicationContext(),"Invalid Username:",Toast.LENGTH_SHORT).show();
                }
                else{
                startActivity(userActivity);
            }}
        }});

    }
boolean isOnline()
{
    ConnectivityManager connectivityManager =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
}
}
