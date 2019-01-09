package com.example.baanu.culte_xchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.facebook.AccessTokenManager.TAG;

public class EditProfile extends Activity {
    private EditText newpassword;
    private Button validate;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        newpassword = (EditText) findViewById(R.id.passwordedit);
        validate =(Button) findViewById(R.id.validate);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //  String newPassword = "SOME-SECURE-PASSWORD";

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!String.valueOf(newpassword.getText()).isEmpty()) {
                    user.updatePassword(String.valueOf(newpassword.getText()))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User password updated.");
                                        Toast.makeText(EditProfile.this, "Password updated", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(EditProfile.this, MembersSeekingActivity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Intent intent2 = new Intent(EditProfile.this, MembersSeekingActivity.class);
                                        startActivity(intent2);
                                    }
                                }
                            });
                }
            }
        });
        button = (Button) findViewById(R.id.button_menu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

    }
    public void openActivity() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}