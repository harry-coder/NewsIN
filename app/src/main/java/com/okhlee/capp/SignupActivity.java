package com.okhlee.capp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.okhlee.capp.CustomElements.CustomFontTextView;
import com.okhlee.capp.CustomElements.CustomTransition;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_userName, et_password, et_confirmPassword;
    private CustomFontTextView bt_signUp;
    private ImageView im_backButton;
    private String mSignupUsername, mSignupPassword, mSignupConfirmpassword;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomTransition.slideActivity(this);

        setContentView(R.layout.activity_signup);

        bindViews();

        mAuth = FirebaseAuth.getInstance();

        bt_signUp.setOnClickListener(this);
        im_backButton.setOnClickListener(this);

        mProgressDialog = new ProgressDialog(this);
    }

    private void bindViews() {

        et_userName = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_confirmPassword = (EditText) findViewById(R.id.et_newPassword);
        im_backButton = (ImageView) findViewById(R.id.im_backbutton);
        bt_signUp = (CustomFontTextView) findViewById(R.id.bt_signup);
    }

    @Override
    public void onClick(View v) {
        if (v == bt_signUp) {
            if (validateEntries()) {
                firebaseCreateNewUser();
            }
        }
        if(v==im_backButton)
        {
            this.finish();
        }

    }

    private void firebaseCreateNewUser() {

        mProgressDialog.setMessage("Registering User..");
        mProgressDialog.show();
        mAuth.createUserWithEmailAndPassword(mSignupUsername, mSignupPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Verification mail has been sent to your email", Toast.LENGTH_SHORT).show();
                    mAuth.getCurrentUser().sendEmailVerification();
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    mProgressDialog.dismiss();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });
    }

    private boolean validateEntries() {
        mSignupUsername = et_userName.getText().toString();
        mSignupPassword = et_password.getText().toString();
        mSignupConfirmpassword = et_confirmPassword.getText().toString();

        if (TextUtils.isEmpty(mSignupUsername)) {
            et_userName.setError("Field cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(mSignupPassword)) {
            et_password.setError("Field cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(mSignupConfirmpassword)) {
            et_confirmPassword.setError("Field cannot be empty ");
            return false;
        }
        if (!mSignupConfirmpassword.equals(mSignupPassword)) {
            et_confirmPassword.setError("Password Doesn't match ");
            return false;
        }

        return true;

    }
}
