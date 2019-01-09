package com.example.baanu.culte_xchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.*;

public class ContactUsActivity extends Activity {

    private Button button;

    private static Pattern pattern;
    private static Matcher matcher;

    Button send;
    EditText fullname, Mail, message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        button = (Button) findViewById(R.id.button_menuCU);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        send = (Button) findViewById(R.id.signup);
        fullname= (EditText) findViewById(R.id.full_name);
        Mail = (EditText) findViewById(R.id.mail);
        message = (EditText) findViewById(R.id.message);

    }
    public void openActivity() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
    public void sended(View view) {

        if (fullname.getText() == null) {

            Toast.makeText(this, "You have forgotten to fulfill your full name", Toast.LENGTH_SHORT);
        }
        else if (Mail.getText() == null) {

            Toast.makeText(this, "You have forgotten to fulfill your mail", Toast.LENGTH_SHORT);
        }
        else if (message.getText() == null) {

            Toast.makeText(this, "You have forgotten to fulfill your message", Toast.LENGTH_SHORT);
        }
        else if (!Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", Mail.getText())) {

            Toast.makeText(this, "Your mail is wrong ! ", Toast.LENGTH_SHORT);
        }

    }
}
