package com.okhlee.capp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.okhlee.capp.CustomElements.CustomFontTextView;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private CustomFontTextView tv_login, tv_facebook, tv_forgetpassword, tv_createaccount;
    private EditText et_username, et_password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mProgressDialog;

    private String mUsername, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_activity);

        bindViews();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mAuth.getCurrentUser() != null) {
                    if (mAuth.getCurrentUser().isEmailVerified()) {

                   //This is where our main news activity will pop up after user has signedup
                        Toast.makeText(LoginActivity.this, "New screen will pop up", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        };
        mProgressDialog = new ProgressDialog(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    private void bindViews() {
        //this will bind all the values
        tv_login = (CustomFontTextView) findViewById(R.id.tv_login);
        tv_createaccount = (CustomFontTextView) findViewById(R.id.tv_createaccount);
        tv_facebook = (CustomFontTextView) findViewById(R.id.tv_facebook);
        tv_forgetpassword = (CustomFontTextView) findViewById(R.id.tv_forgetpassword);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        tv_login.setOnClickListener(this);
        tv_createaccount.setOnClickListener(this);
        tv_facebook.setOnClickListener(this);
        tv_forgetpassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == tv_login) {


            if (validateEntries()) {
                //calling fireBase method for user login..
                startLoginTask();
            }
        }
        if (v == tv_createaccount) {
            startActivity(new Intent(this, SignupActivity.class));
        }
        if (v == tv_forgetpassword) {

        }
        if (v == tv_facebook) {

        }


    }


    private void startLoginTask() {

        mProgressDialog.setMessage("Logging in...");
        mProgressDialog.show();
        mAuth.signInWithEmailAndPassword(mUsername, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (mAuth.getCurrentUser().isEmailVerified()) {
                    if (task.isSuccessful()) {
                        //
                        Toast.makeText(LoginActivity.this, "New Activity", Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();
                    } else {

                        Toast.makeText(LoginActivity.this, "Please Check your credentials ", Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Please Verify your email address first!", Toast.LENGTH_SHORT).show();
                    mProgressDialog.dismiss();
                }
            }
        });

    }

    public boolean validateEntries() {


        mUsername = et_username.getText().toString();
        mPassword = et_password.getText().toString();

        if (TextUtils.isEmpty(mUsername)) {
            et_username.setError("Field cannot be blank");
            return false;
        }
        if (TextUtils.isEmpty(mPassword)) {
            et_password.setError("Field cannot be empty");
            return false;
        }
        return true;


    }
}
