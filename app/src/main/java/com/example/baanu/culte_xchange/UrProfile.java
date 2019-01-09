package com.example.baanu.culte_xchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UrProfile extends Activity {
    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ur_profile);
        button1=(Button) findViewById(R.id.signup);
        button2=(Button) findViewById(R.id.button4);
        button3=(Button) findViewById(R.id.button5);
        button4=(Button) findViewById(R.id.button6);
        button5=(Button) findViewById(R.id.button7);
        button6=(Button) findViewById(R.id.button8);
        button = (Button) findViewById(R.id.button_menuP);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModify();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModify();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModify();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModify();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModify();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModify();
            }
        });
    }

    public void openModify() {
        Intent intent1 = new Intent(this, EditProfile.class);
        startActivity(intent1);
    }

    public void openActivity() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}
