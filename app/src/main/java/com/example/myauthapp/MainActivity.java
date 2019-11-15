package com.example.myauthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail ,editTextPswd;




    //private TextView textViewSignin;

   /**private EditText editTextEmail;
    private EditText editTextPswd;
    private Button signupButton;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;*/

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPswd = (EditText) findViewById(R.id.editTextPswd);



        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signupButton).setOnClickListener(this);



       /** ProgressDialog progressDialog = new ProgressDialog(this);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPswd = (EditText) findViewById(R.id.editTextPswd);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        signupButton = (Button) findViewById(R.id.signupButton);


        signupButton.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);*/


    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        /**FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);*/

        if (mAuth.getCurrentUser() != null){

        }


    }

    public void registerUser(){

        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPswd.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("email required");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPswd.setError("password required");
            editTextPswd.requestFocus();
            return;
        }
        if (password.length() < 8) {
            editTextPswd.setError("Password should be at least 8 characters long");
            editTextPswd.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    User user = new User(
                            email

                    );

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                displayToast("Registration Successful");
                            } else {
                                displayToast("Registration Unsuccessful");
                            }
                        }
                    });

                } else {
                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.signupButton:
                registerUser();
                break;
        }

    }


    /**private void updateUI(FirebaseUser currentUser) {

    }

    /**@Override
    public void onClick(View v) {
        if (v == signupButton){
            registerUser();
        }
        if (v == textViewSignin){
            //open login activity here
        }

    }

    private void registerUser() {
        //get email and pswd from editTexts
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPswd.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email field is empty
            Toast.makeText(this, "Please Enter Your Email Address",Toast.LENGTH_SHORT).show();
            //halt execution
            return;
        }
        if (TextUtils.isEmpty(password)){
            //password field is empty
            Toast.makeText(this, "Please Enter Your Password",Toast.LENGTH_SHORT).show();
            //halt execution
            return;
        }

        //if all fields are satisfied
        //progressbar will be displayed

        progressDialog.setMessage("Registering User....");
        progressDialog.show();

        //takes to args email and pswd
        //also it creates a user in the fireBase console with the given details
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    //this method will be executed registration is completed.
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user =mAuth.getCurrentUser();
                            updateUI(user);
                            //user is successfully registered and logged in!!
                            Toast.makeText(MainActivity.this, "User Registration Successful", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(MainActivity.this, "User Registration Failed!! Please Try Again", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });


    }*/
}
