package com.afq.a220204445;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextTextPersonName;
    private EditText mEditTextTextPersonName2;
    private EditText mEditTextTextPersonName3;
    private Button mButton;
    private ListView mList;
    ArrayList<Conc> concs;
    Adap adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        mEditTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        mEditTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        mButton = findViewById(R.id.button);
        mList = findViewById(R.id.list);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addname(mEditTextTextPersonName.getText().toString());

                adapter=new Adap(concs,MainActivity.this);
                mList.setAdapter(adapter);

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                String s1 =mEditTextTextPersonName.getText().toString();
                String s2= (mEditTextTextPersonName2.getText().toString());
                int s3= Integer.parseInt(mEditTextTextPersonName3.getText().toString());

                Conc conc=new Conc();
                conc.setName(s1);
                conc.setAddress(s2);
                conc.setPhone(s3);

                database.getReference().child("conc").push().setValue(conc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "the value success complete", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                database.getReference().child("conc").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        concs=new ArrayList<>();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            Conc conc1=dataSnapshot1.getValue(Conc.class);


                            conc1.getName();
                            conc1.getAddress();
                            conc1.getPhone();
                            concs.add(conc1);



                        }


                        adapter.notifyDataSetChanged();

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        getallnames();
    }

    public void  getallnames(){
        final  ArrayList<Conc>concss=new ArrayList<>();

        adapter=new Adap(concs,MainActivity.this);
        mList.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("conc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                concss.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    Conc conc=snap.getValue(Conc.class);
                    conc.setId(snap.getKey());
                    concss.add(conc);
                    conc.getName();

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addname(String name){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Conc conc=new Conc();

        database.getReference().child("conc").push().setValue(conc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "تم اضافة البيانات", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    }


