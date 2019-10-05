package com.kumabites.mm.moneymanagement;

import MMENTITY.User;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kumabites.mm.R;
import com.kumabites.mm.moneymanagement.CreateUser.NewUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;


public static AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         user = (EditText)findViewById(R.id.userText);
         pass =(EditText)findViewById(R.id.passwordText);
        appDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"userdb").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
    public void registerNew(View view){
        Intent rIntent = new Intent(this, NewUser.class);
        startActivity(rIntent);
        finish();
    }

    public String getOUser(List<User> oldUserList) {

        for (User user : oldUserList) {
            String userid = user.getUser();
            return userid;
        }
    return null;}

    public String getOPass(List<User> oldUserList) {

        for (User user : oldUserList) {
            String passid = user.getPassword();
            return passid;
        }
        return null;
    }

    public void userCheck (String oldUser, String newUser, String oldPass, String newPass) {
        Intent Main = new Intent(this, MainPage.class);
        if (oldUser == null ||  oldPass == null){
            user.setText("");
            pass.setText("");
            Toast.makeText(getBaseContext(), "Wrong details", Toast.LENGTH_SHORT).show();}
        else if ( oldUser.equals(newUser)&& oldPass.equals(newPass)) {
                CurrentUser.setUsername(oldUser);
                startActivity(Main);
                finish();
                Toast.makeText(getBaseContext(), "Welcome " + oldUser, Toast.LENGTH_SHORT).show();
            }
        else {
            user.setText("");
            pass.setText("");
            Toast.makeText(getBaseContext(), "Wrong details", Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    {

    }
    public void exit(View view){
        System.exit(0);
    }

    public void nuketable(View view){
        List<User> check = MainActivity.appDatabase.userDao().getAnyUser();
        if(check.isEmpty())
        {Toast.makeText(getBaseContext(),"There are no users to delete!",Toast.LENGTH_SHORT).show();}
        else{
        appDatabase.clearAllTables();

        Toast.makeText(getBaseContext(),"All Data Deleted!!",Toast.LENGTH_SHORT).show();
    }}
    public void oldUser(View view) {
        String oldUser = user.getText().toString();
        String oldPass = pass.getText().toString();
        List<User> getOldUser = appDatabase.userDao().findUser(oldUser, oldPass);
        String oldU = getOUser(getOldUser);
        String oldP = getOPass(getOldUser);
        userCheck(oldU,oldUser,oldP,oldPass);


    }
}


