package com.okhlee.capp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import com.okhlee.capp.CustomElements.CustomFontTextView;
import com.okhlee.capp.fragments.loginFragment;



public class LoginActivity extends AppCompatActivity {
    CustomFontTextView tv_login, tv_facebook, tv_forgetpassword, tv_createaccount;
    EditText et_username, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        //adding login fragment..
        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new loginFragment()).commit();


        // bindViews();

    }


    private void listeners() {
        //this will handle all event listeners
    }
}
