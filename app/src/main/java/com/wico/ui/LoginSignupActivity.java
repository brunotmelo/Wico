package com.wico.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.wico.R;
import com.wico.datatypes.Question;
import com.wico.network.ParseConnector;


public class LoginSignupActivity extends AppCompatActivity {

        Button loginbutton;
        Button signup;
        String usernametxt;
        String passwordtxt;
        EditText password;
        EditText username;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            connectToParse();
            setContentView(R.layout.login_activity);

            Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.login_toolbar);
            setSupportActionBar(mActionBarToolbar);
            String loginTitle = getResources().getString(R.string.wico_login);
            getSupportActionBar().setTitle(loginTitle);

            username = (EditText) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);
            loginbutton = (Button) findViewById(R.id.login);
            signup = (Button) findViewById(R.id.signup);
            username.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            password.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            loginbutton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View arg0) {
                    usernametxt = username.getText().toString();
                    passwordtxt = password.getText().toString();
                    ParseUser.logInInBackground(usernametxt, passwordtxt,
                            new LogInCallback() {

                                public void done(ParseUser user, ParseException e) {
                                    if (user != null) {
                                        loginSuccess();
                                    } else {
                                        nonExistentUser();

                                    }
                                }
                            });
                }
            });

            signup.setOnClickListener(new View.OnClickListener() {

                public void onClick(View arg0) {

                    usernametxt = username.getText().toString();
                    passwordtxt = password.getText().toString();
                    if (usernametxt.equals("") && passwordtxt.equals("")) {
                        onNullTextSignUpClick();

                    } else {
                        ParseUser user = saveNewUser();
                        user.signUpInBackground(new SignUpCallback() {

                            public void done(ParseException e) {
                                if (e == null) {
                                    successfulSignUp();
                                } else {
                                    displaySignUpError();
                                }
                            }
                        });
                    }
                }
            });
        }


    private void loginSuccess(){

        Intent intent = new Intent(
                LoginSignupActivity.this,
                PageActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),
                "Successfully logged in to Wico",
                Toast.LENGTH_LONG).show();
        finish();
    }

    private void nonExistentUser(){
        Toast.makeText(
                getApplicationContext(),
                "This user does not exist, please sign up",
                Toast.LENGTH_LONG).show();
    }

    private void onNullTextSignUpClick(){
        Toast.makeText(getApplicationContext(),
                "Please sign up",
                Toast.LENGTH_LONG).show();
    }

    private ParseUser saveNewUser(){
        ParseUser user = new ParseUser();
        user.setUsername(usernametxt);
        user.setPassword(passwordtxt);
        return user;
    }

    private void successfulSignUp(){
        Toast.makeText(getApplicationContext(),
                "Successfully Signed up, please log in.",
                Toast.LENGTH_LONG).show();
    }

    private void displaySignUpError(){
        Toast.makeText(getApplicationContext(),
                "Sign up Error", Toast.LENGTH_LONG)
                .show();
    }

    private void connectToParse() {
        ParseConnector connector = new ParseConnector();
        connector.initialize(this);
    }
}

