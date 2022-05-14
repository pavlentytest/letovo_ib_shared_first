package ru.samsung.itschool.mdev.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText name, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
    }

    public void saveData(View view) {
        SharedPreferences preferences = getSharedPreferences("DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Name",name.getText().toString());
        editor.putInt("Age",Integer.parseInt(age.getText().toString()));
        editor.apply(); // save to file
    }
}