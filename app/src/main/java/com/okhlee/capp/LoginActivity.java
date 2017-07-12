package com.okhlee.capp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.okhlee.capp.CustomElements.CustomFontTextView;

public class LoginActivity extends AppCompatActivity {
    CustomFontTextView tv_login,tv_facebook,tv_forgetpassword,tv_createaccount;
    EditText et_username,et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        bindViews();
    }

    private void bindViews() {
        //this will bind all the values
        tv_login =(CustomFontTextView)findViewById(R.id.tv_login);
        tv_createaccount =(CustomFontTextView)findViewById(R.id.tv_createaccount);
        tv_facebook =(CustomFontTextView)findViewById(R.id.tv_facebook);
        tv_forgetpassword =(CustomFontTextView)findViewById(R.id.tv_forgetpassword);
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        listeners();
    }

    private void listeners() {
        //this will handle all event listeners
    }
}
