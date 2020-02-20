package com.kumabites.mm.moneymanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kumabites.mm.R;

import MMENTITY.User;
import androidx.appcompat.app.AppCompatActivity;

public class NewUser extends AppCompatActivity {
    private EditText userInput, passInput;
    private String userNew, passNew;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        userInput = findViewById(R.id.UserText);
        passInput = findViewById(R.id.PassText);
        appDatabase = AppDatabase.getDatabase(this);

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
       // List<User> oneUser = appDatabase.userDao().findUserSingle(user);
       // if (oneUser.isEmpty()) {
            userNew = userInput.getText().toString();
            passNew = passInput.getText().toString();
            User newUser = new User();
            newUser.setUser(userNew);
            newUser.setPassword(passNew);
            new insertUserTask(newUser).execute();
            Toast.makeText(getBaseContext(), "User Added Successfully", Toast.LENGTH_SHORT).show();
            Intent backToMain = new Intent(this, MainActivity.class);
            startActivity(backToMain);
            finish();
        //} else {
        //    Toast.makeText(getBaseContext(), "Already a user registered", Toast.LENGTH_SHORT).show();
       // }

    }

    //stops the back button on the phone from working
        @SuppressLint("MissingSuperCall")
        @Override
        public void onBackPressed ()
        {

        }



    private class insertUserTask extends AsyncTask<Void, Void, Void> {

        private User newUser;
        private AppDatabase db;

        public insertUserTask(User newUser){
            this.newUser = newUser;
            db = AppDatabase.getDatabase(NewUser.this);
        }
        @Override
        protected Void doInBackground(Void... params) {

            db.userDao().insertUser(newUser);
            return null;
            }
        }


        }






