package com.example.baanu.culte_xchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class inscription extends Activity {
    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText password;
    private EditText birthdate;
    private EditText country;
    private Button register;
    private FirebaseAuth fauth;
    private String UserIdAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        firstname = (EditText) findViewById(R.id.firstnameedit);
        lastname = (EditText) findViewById(R.id.lastnameedit);
        email = (EditText) findViewById(R.id.mailedit);
        password = (EditText) findViewById(R.id.password);
        birthdate = (EditText) findViewById(R.id.dateedit);
        country = (EditText) findViewById(R.id.countryedit);
        register =(Button) findViewById(R.id.validate);
        fauth = FirebaseAuth.getInstance();
        UserIdAuth = fauth.getUid();
        int i = new Random().nextInt();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //final DatabaseReference mRef = database.getReference("Utilisateur/User");
        final DatabaseReference Fname = database.getReference("Utilisateur/User"+i+"/FirstName");
        final DatabaseReference Lname = database.getReference("Utilisateur/User"+i+"/LastName");
        final DatabaseReference Mail = database.getReference("Utilisateur/User"+i+"/Email");
        final DatabaseReference Pass = database.getReference("Utilisateur/User"+i+"/Password");
        final DatabaseReference Bday = database.getReference("Utilisateur/User"+i+"/Birthdate");
        final DatabaseReference Pays = database.getReference("Utilisateur/User"+i+"/Country");
        final DatabaseReference UID = database.getReference("Utilisateur/User"+i+"/UID");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty())return;
                inProgress(true);
                fauth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(inscription.this,"User registred",Toast.LENGTH_LONG).show();
                                inProgress(false);

                                String fname = firstname.getText().toString();
                                String lname = lastname.getText().toString();
                                String mail = email.getText().toString();
                                String pwd = password.getText().toString();
                                String bday = birthdate.getText().toString();
                                String pays = country.getText().toString();
                                Fname.setValue(fname);
                                Lname.setValue(lname);
                                Mail.setValue(mail);
                                Pass.setValue(pwd);
                                Bday.setValue(bday);
                                Pays.setValue(pays);
                                UID.setValue(UserIdAuth);

                                Intent intent=new Intent(inscription.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(inscription.this,"Registration failed : " + e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
    private void inProgress(boolean x){
        if(x){
            register.setEnabled(false);
        }else{
            register.setEnabled(true);
        }
    }
    private boolean isEmpty(){
        if(TextUtils.isEmpty(email.getText().toString())){
            email.setError("REQUIRED");
            return true;
        }
        if(TextUtils.isEmpty(password.getText().toString())){
            password.setError("REQUIRED");
            return true;
        }
        return false;
    }

}
