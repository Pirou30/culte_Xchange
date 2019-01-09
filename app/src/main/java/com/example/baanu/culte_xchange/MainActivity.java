package com.example.baanu.culte_xchange;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.Login;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends Activity {
    private EditText tmail;
    private EditText tpwd;
    private Button bconn;
    private FirebaseAuth fauth;
    private Button signup;
    CallbackManager callbackManager;
    ProgressDialog mDialog;

    protected void onActivityResult(int requestcode, int resultcode, Intent data){
        super.onActivityResult(requestcode,resultcode,data);
        callbackManager.onActivityResult(requestcode,resultcode,data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        tmail = (EditText) findViewById(R.id.mail_editText);
        tpwd = (EditText) findViewById(R.id.pwd_editText);
        bconn = (Button) findViewById(R.id.connect_button);
        fauth = FirebaseAuth.getInstance();

        signup = (Button) findViewById(R.id.signup);

        bconn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty())return;
                inProgress(true);
                fauth.signInWithEmailAndPassword(tmail.getText().toString(),tpwd.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(MainActivity.this,"User signned in",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(MainActivity.this,MembersSeekingActivity.class);
                            //    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();return;
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(MainActivity.this,"Sign in failed : " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });



        callbackManager= CallbackManager.Factory.create();
        final LoginButton loginButton = (LoginButton)findViewById(R.id.imageButton4);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
              mDialog=new ProgressDialog(MainActivity.this);
              mDialog.setMessage("Retrieving data ...");
              Intent intent1 = new Intent(MainActivity.this, MembersSeekingActivity.class);
              startActivity(intent1);
              mDialog.show();
              String accesstoken = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        getData(object);
                    }
                });
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }


    public void openActivity() {
        Intent intent = new Intent(this, inscription.class);
        startActivity(intent);
    }


    private void inProgress(boolean x){
        if(x){
            bconn.setEnabled(false);
            signup.setEnabled(false);
        }else{
            bconn.setEnabled(true);
            signup.setEnabled(true);
        }
    }

    private boolean isEmpty(){
        if(TextUtils.isEmpty(tmail.getText().toString())){
            tmail.setError("REQUIRED");
            return true;
        }
        if(TextUtils.isEmpty(tpwd.getText().toString())){
            tpwd.setError("REQUIRED");
            return true;
        }
        return false;
    }

    private void getData(JSONObject object) {
        try{
             URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id"+"/picture?width=250&height=250 "));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    };

    private void printKeyHash(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.baanu.culte_xchange", PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
