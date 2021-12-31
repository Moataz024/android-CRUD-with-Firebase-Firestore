package com.example.projetfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateActivity extends AppCompatActivity {

    EditText phone, fullname;
    NumberPicker age;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference personRef = db.collection("Person");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        Person person = new Person(getIntent().getExtras().getString("fullname"), getIntent().getExtras().getString("phone"), getIntent().getExtras().getInt("age"));

        setTitle("Edit " + person.getFullName());

        fullname = findViewById(R.id.fullname);
        phone = findViewById(R.id.phone);
        age = findViewById(R.id.age);

        age.setMinValue(1);
        age.setMaxValue(100);

        fullname.setText(person.getFullName());
        phone.setText(person.getPhone());
        age.setValue(person.getAge());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_person_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save:
                UpdatePerson();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void UpdatePerson(){
        String fname =  fullname.getText().toString();
        String tel = phone.getText().toString();
        int AGE = age.getValue();
        String id = getIntent().getExtras().getString("ID");

        if (fname.trim().isEmpty() || tel.trim().isEmpty()){
            Toast.makeText(this, "Remplir tous les champs SVP!", Toast.LENGTH_SHORT).show();
            return;
        }
        personRef.document(id)
                .update(
                        "age", AGE,
                        "fullName", fname,
                        "phone", tel
                ).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Intent k = new Intent();
                setResult(RESULT_OK,k);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Intent k = new Intent();
                setResult(RESULT_CANCELED, k);
                finish();
            }
        });

    }
}