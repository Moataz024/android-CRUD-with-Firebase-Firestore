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

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;



public class InsertActivity extends AppCompatActivity {
    private EditText fullname,phone;
    private NumberPicker age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        setTitle("Insert Person");

        fullname = findViewById(R.id.fullname);
        phone = findViewById(R.id.phone);
        age = findViewById(R.id.age);

        age.setMinValue(1);
        age.setMaxValue(100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_person_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.save:
                InsertNewPerson();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void InsertNewPerson(){
        String fname =  fullname.getText().toString();
        String tel = phone.getText().toString();
        int AGE = age.getValue();

        if (fname.trim().isEmpty() || tel.trim().isEmpty()){
            Toast.makeText(this, "Remplir tous les champs SVP!", Toast.LENGTH_SHORT).show();
            return;
        }
        CollectionReference personRef = FirebaseFirestore.getInstance().collection("Person");
        personRef.add(new Person(fname,tel,AGE));
        Toast.makeText(this, "Person added!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}