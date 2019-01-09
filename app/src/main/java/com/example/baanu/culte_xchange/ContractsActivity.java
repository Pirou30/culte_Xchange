package com.example.baanu.culte_xchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContractsActivity extends Activity {
    private Button button;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contracts);
        button = (Button) findViewById(R.id.button_menuC);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        button1 = (Button) findViewById(R.id.validate);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });
    }
    public void openActivity() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
    public void openActivity1() {
        Intent intent = new Intent(this, RatingActivity.class);
        startActivity(intent);
    }
}
