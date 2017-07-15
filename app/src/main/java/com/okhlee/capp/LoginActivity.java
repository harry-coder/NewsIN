package com.okhlee.capp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.okhlee.capp.CustomElements.CustomFontTextView;


import java.util.Collections;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private CustomFontTextView tv_login, tv_facebook, tv_forgetpassword, tv_createaccount;
    private EditText et_username, et_password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mProgressDialog;

    private String mUsername, mPassword;

    private static int fb_Signin=1;
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

                        startActivity(new Intent(LoginActivity.this,NewsActivity.class));


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

            facebookLogin();
        }


    }

    private void facebookLogin() {

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Collections.singletonList(
                                        new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()
                                ))
                        .build(), fb_Signin);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==fb_Signin) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                startActivity(new Intent(LoginActivity.this, NewsActivity.class));
            }
            if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                Toast.makeText(this, "NO internet connection", Toast.LENGTH_SHORT).show();

                return;
            }

            if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                return;
            }

        }
    }

    private void startLoginTask() {

        mProgressDialog.setMessage("Logging in...");
        mProgressDialog.show();
        mAuth.signInWithEmailAndPassword(mUsername, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (mAuth.getCurrentUser() != null) {
                    if (mAuth.getCurrentUser().isEmailVerified()) {
                        if (task.isSuccessful()) {
                            //
                            startActivity(new Intent(LoginActivity.this,NewsActivity.class));
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
                else
                {
                    Toast.makeText(LoginActivity.this, "User Doesn't exist. SignUp First!", Toast.LENGTH_SHORT).show();
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
