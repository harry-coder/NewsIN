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
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.okhlee.capp.CustomElements.CustomFontTextView;
import com.okhlee.capp.CustomElements.CustomToast;
import com.okhlee.capp.CustomElements.CustomTransition;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    private EditText et_userName, et_password, et_newPassword;
    private FirebaseUser currentUser;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;
    private ImageView im_backButton;
    private CustomFontTextView bt_login,bt_resetPassword;

    String mForgotUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomTransition.slideActivity(this);
        setContentView(R.layout.activity_forgot_password);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        bindView();

        bt_resetPassword.setOnClickListener(this);
        im_backButton.setOnClickListener(this);
        bt_login.setOnClickListener(this);

        mProgressDialog = new ProgressDialog(this);
    }

    private void bindView() {

        et_userName = (EditText) findViewById(R.id.et_username);
        bt_resetPassword = (CustomFontTextView) findViewById(R.id.bt_resetPassword);
        bt_login = (CustomFontTextView) findViewById(R.id.bt_login);
        im_backButton = (ImageView) findViewById(R.id.im_backbutton);

    }


    private boolean validateEntries() {
        mForgotUsername = et_userName.getText().toString().trim();

        if (TextUtils.isEmpty(mForgotUsername)) {
            et_userName.setError("Field cannot be empty");
            return false;
        }

        return true;

    }

    @Override
    public void onClick(View v) {

        if(v==bt_login)
        {
            startActivity(new Intent(ForgotPassword.this,LoginActivity.class));
        }
        if(v==bt_resetPassword) {
            if (validateEntries()) {
                forgotPasswordd();
            }
        }
        if(im_backButton==v)
        {
            finish();
        }

    }

    /*public void showSnackbar(String subject)
    {
        ConstraintLayout constraintsLayout= (ConstraintLayout) findViewById(R.id.forgotPasswordRoot);
        Snackbar snackbar = Snackbar.make(constraintsLayout, subject, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        CoordinatorLayout.LayoutParams params= (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        snackbar.show();
    }
*/
    private void forgotPasswordd() {
        mProgressDialog.setMessage("Resetting..");
        mProgressDialog.show();

        mAuth.sendPasswordResetEmail(mForgotUsername)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {


                            CustomToast.showToast("instructions has been sent at your mailing address!",ForgotPassword.this);

                            mProgressDialog.dismiss();
                        } else {

                            CustomToast.showToast("Failed to send reset email!",ForgotPassword.this);

                            mProgressDialog.dismiss();
                        }

                    }
                });
    }




private void forgotPassword(){
        if(currentUser!=null){
        mProgressDialog.setMessage("Resetting..");
        mProgressDialog.show();
        currentUser.updatePassword(et_newPassword.getText().toString().trim())
        .addOnCompleteListener(new OnCompleteListener<Void>(){
@Override
public void onComplete(@NonNull Task<Void> task){
        if(task.isSuccessful()){
        CustomToast.showToast("Password Updated",ForgotPassword.this);
        mProgressDialog.dismiss();
        startActivity(new Intent(ForgotPassword.this,LoginActivity.class));

        }else{

        CustomToast.showToast("Please check username",ForgotPassword.this);

        //   showSnackbar("Please Check Username");
        mProgressDialog.dismiss();
        }
        }
        });

        }else{
        //  Toast.makeText(this, "Please SignUp first", Toast.LENGTH_SHORT).show();
        CustomToast.showToast("You are not the registered user",ForgotPassword.this);

        }
        }

        }
