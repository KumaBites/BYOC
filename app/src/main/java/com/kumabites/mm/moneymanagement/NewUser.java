package com.kumabites.mm.moneymanagement;

import MMENTITY.User;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kumabites.mm.R;

public class NewUser extends AppCompatActivity {
private EditText user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        user = (EditText)findViewById(R.id.UserText);
        pass = (EditText)findViewById(R.id.PassText);
    }

    public void back(View back) {
        Intent goBack = new Intent(this,MainActivity.class);
        startActivity(goBack);

    }
    public void OnClick (View view){

        String userNew = user.getText().toString();
        String passNew = pass.getText().toString();


            User user = new User();
            user.setUser(userNew);
            user.setPassword(passNew);
            MainActivity.appDatabase.userDao().insertUser(user);
            Toast.makeText(getBaseContext(), "User Added Successfully", Toast.LENGTH_SHORT).show();


    }

}
