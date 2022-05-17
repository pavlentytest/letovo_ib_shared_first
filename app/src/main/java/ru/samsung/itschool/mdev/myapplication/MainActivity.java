package ru.samsung.itschool.mdev.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
// GSON - Google SON

public class MainActivity extends AppCompatActivity {

    private EditText name, age;
    private ArrayList<User> users = new ArrayList<>();
    private ListView userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        userlist = findViewById(R.id.list);
        getData();
    }

    public void addUser(View view) {
        User user = new User(name.getText().toString(),Integer.parseInt(age.getText().toString()));
        users.add(user);
        name.setText("");
        age.setText("");
    }

    public void saveData(View view) {
        SharedPreferences preferences = getSharedPreferences("DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String s = gson.toJson(users);
        editor.putString("USERS_ARRAYLIST",s);
        editor.apply(); // save to file
        getData();
    }

    public void getData() {
        SharedPreferences preferences = getSharedPreferences("DATA", MODE_PRIVATE);
        String str_list = preferences.getString("USERS_ARRAYLIST", "Empty!");
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        Gson gson = new Gson();
        ArrayList<User> arrayList = gson.fromJson(str_list,type);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                arrayList
        );
        userlist.setAdapter(arrayAdapter);

    }
}