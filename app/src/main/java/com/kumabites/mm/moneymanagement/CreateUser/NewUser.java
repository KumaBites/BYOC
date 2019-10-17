package com.kumabites.mm.moneymanagement.CreateUser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kumabites.mm.R;
import com.kumabites.mm.moneymanagement.MainActivity;

import java.util.List;

import MMENTITY.User;
import androidx.appcompat.app.AppCompatActivity;

public class NewUser extends AppCompatActivity {
    private EditText userInput, passInput;
    private String userNew, passNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        userInput = findViewById(R.id.UserText);
        passInput = findViewById(R.id.PassText);


    }


    public void back(View view){
        Intent goBack = new Intent(this, MainActivity.class);
        startActivity(goBack);
        finish();

    }
    public void checkUser (View view){
        userNew = userInput.getText().toString();
        singleUser(userNew);}

    public void singleUser(String user) {
        List<User> oneUser = MainActivity.appDatabase.userDao().findUser(user);
        if (oneUser.isEmpty()) {
            userNew = userInput.getText().toString();
            passNew = passInput.getText().toString();

            User newUser = new User();
            newUser.setUser(userNew);
            newUser.setPassword(passNew);
            MainActivity.appDatabase.userDao().insertUser(newUser);
            Toast.makeText(getBaseContext(), "User Added Successfully", Toast.LENGTH_SHORT).show();
            Intent backToMain = new Intent(this, MainActivity.class);
            startActivity(backToMain);
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Already a user registered", Toast.LENGTH_SHORT).show();
        }

    }

    //stops the back button on the phone from working
        @SuppressLint("MissingSuperCall")
        @Override
        public void onBackPressed ()
        {

        }

}

