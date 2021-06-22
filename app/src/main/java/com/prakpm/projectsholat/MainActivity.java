package com.prakpm.projectsholat;

import android.content.Intent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shalatreminder.R;

public class MainActivity extends AppCompatActivity {
    EditText email, pass;
    TextView sign;
    Button login;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.et_email);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btn_login);

        TextView textView = findViewById(R.id.tv_signup);
        String text = "Don't have an account? Sign Up.";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        };

        ss.setSpan(clickableSpan, 23,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        sharedPreferences = getSharedPreferences("loginref", MODE_PRIVATE);
        editor = sharedPreferences.edit();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


        savelogin = sharedPreferences.getBoolean("savelogin", false);
        if (savelogin == true) {
            email.setText(sharedPreferences.getString("email", null));
            pass.setText(sharedPreferences.getString("password", null));
            Intent intent = new Intent(MainActivity.this, com.example.shalatreminder.Main2Activity.class);
            startActivity(intent);
        }
    }

    public void login() {
        String usrname = email.getText().toString();
        String passwoord = pass.getText().toString();
        if (usrname.equals("admin@gmail.com") && passwoord.equals("123")) {
            Toast.makeText(this, "LOGIN BERHASIL!", Toast.LENGTH_LONG).show();
            editor.putBoolean("savelogin", true);
            editor.putString("username", usrname);
            editor.putString("password", passwoord);
            editor.commit();
            Intent intent = new Intent(MainActivity.this, com.example.shalatreminder.Main2Activity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "EMAIL DAN PASSWORD SALAH/TIDAK TERDAFTAR!", Toast.LENGTH_LONG).show();
        }
    }

}