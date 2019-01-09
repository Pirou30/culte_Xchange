package com.example.baanu.culte_xchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MembersSeekingActivity extends Activity {
    private Button button;
    private String userID;
    private String userKey;
    private FirebaseAuth fauth;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    //private TextView textView;
    private HashMap<String,String> nameCountry=new HashMap<>();
    private List<HashMap<String,String>> listvue=new ArrayList<>();
    private ArrayList<String> listCountry=new ArrayList<String>();
    private ArrayList<String> listName=new ArrayList<String>();
    //private List<User> userList = new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.members_seeking);
        fauth=FirebaseAuth.getInstance();
        listView = findViewById(R.id.bandView);

        ref = FirebaseDatabase.getInstance().getReference("Utilisateur");
        getInfo();

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
    public void getInfo(){
        final Intent intent=new Intent(MembersSeekingActivity.this,MembersSeekingActivity2.class);

        userID = fauth.getUid();

        ref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String prevChildKey) {
                    User user = dataSnapshot.getValue(User.class);
                    nameCountry.put(user.getFirstName() + " " + user.getLastName(),user.getCountry());
                    SimpleAdapter simpleAdapter=new SimpleAdapter(MembersSeekingActivity.this,listvue,R.layout.list_item,new String[]{"First Line","Second Line"},new int[]{R.id.text1,R.id.text2});
                    Iterator it=nameCountry.entrySet().iterator();
                    while (it.hasNext()){
                        HashMap<String,String> resultMap=new HashMap<>();
                        Map.Entry pair=(Map.Entry) it.next();
                        resultMap.put("First Line",pair.getKey().toString());
                        resultMap.put("Second Line",pair.getValue().toString());
                        listvue.add(resultMap);
                    }

                    listCountry.add(user.getCountry());
                    listName.add(user.getFirstName() + " " +user.getLastName());
                    listName.add(user.getFirstName() + " " + user.getLastName());
                    int i=0;
                    while (i<listCountry.size()){
                        if (moreThanOnce(listCountry,i)){
                            listCountry.remove(i);
                            i=i-1;
                        }
                        i++;
                    }

                //adapter = new ArrayAdapter<String>(MembersSeekingActivity.this, android.R.layout.simple_list_item_1, listCountry);
                listView.setAdapter(simpleAdapter);
                /*Query query = FirebaseDatabase.getInstance().getReference("Utilisateur")
                        .orderByChild("Country")
                        .equalTo("India");*/
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
                //listView.setAdapter(simpleAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public static boolean moreThanOnce(ArrayList<String> list, int searched)
    {
        int numCount = 0;

        for (String thisNum : list) {
            if (thisNum.equals(list.get(searched))) numCount++;
        }

        return numCount > 1;
    }
}
