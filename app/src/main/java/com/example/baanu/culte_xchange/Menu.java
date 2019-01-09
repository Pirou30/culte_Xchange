package com.example.baanu.culte_xchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity {
    Button bprofil;
    Button brequest;
    Button bcontract;
    Button bcontactus;
    Button bhome;
    Button bdeco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bprofil=findViewById(R.id.bprofile);
        brequest=findViewById(R.id.brequests);
        bcontract=findViewById(R.id.bcontracts);
        bcontactus=findViewById(R.id.bcontact);
        bhome=findViewById(R.id.bhome);
        bdeco=findViewById(R.id.bdeco);
        bprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, UrProfile.class);
                startActivity(intent);
            }
        });
        brequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, RequestActivity.class);
                startActivity(intent);
            }
        });
        bcontract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ContractsActivity.class);
                startActivity(intent);
            }
        });
        bcontactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });
        bhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MembersSeekingActivity.class);
                startActivity(intent);
            }
        });
        bdeco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
