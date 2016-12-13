package com.ramadan.firebaseauth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail,etPassword;
    private Button register,database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = etEmail.getText().toString();
                String passwordInput = etPassword.getText().toString();

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(emailInput, passwordInput).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = task.getResult().getUser();
                                } else
                                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                            }
                        });


            }
        });



        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference ref=database.getReference("my_name");
                ref.setValue("Mahmoud Ramadan");

                //send object
                Post post=new Post();
                post.setTitle("title me");
                post.setDescription("It is awesome");
                
                database.getReference("posts").push().setValue(post);

            }
        });
    }

    private void setupView(){
        etEmail=(EditText)findViewById(R.id.email);
        etPassword=(EditText)findViewById(R.id.password);
        register=(Button)findViewById(R.id.register);
        database=(Button)findViewById(R.id.database);

    }




}
