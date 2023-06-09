package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btnLogin;
    TextView tvRegister;
    private BroadcastReceiver Br;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        tvRegister = findViewById(R.id.textViewNewUser);
        img = findViewById(R.id.imgHelpdesk);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"HealthCare",null,1);
               if(username.length()==0 || password.length()==0){
                   Toast.makeText(getApplicationContext(), "Hãy nhập Tên Đăng Nhập và Mật Khẩu", Toast.LENGTH_SHORT).show();
               }
               else{
                   if(db.Login(username,password)==1){
                       Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                       SharedPreferences sp = getSharedPreferences("share_prefs", Context.MODE_PRIVATE);
                       SharedPreferences.Editor editor = sp.edit();
                       editor.putString("username",username);
                       editor.apply();
                       startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                   }
                   else {
                       Toast.makeText(getApplicationContext(), "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, InformationAppActivity.class));
            }
        });
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            Toast.makeText(getApplicationContext(), "Network Connected", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Network Not Connected", Toast.LENGTH_SHORT).show();
        }
        Br = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(Br, intentFilter);


    }
}