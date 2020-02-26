package com.kumabites.mm.moneymanagement;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kumabites.mm.R;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import MMENTITY.User;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    private EditText user;
    private EditText pass;
    private String userid, passid, oldUser, oldPass, oldU, oldP;
    private List<User> findAnyResult;
    AppDatabase appDatabase;
    private Object String;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appDatabase = AppDatabase.getDatabase(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.userText);
        pass = findViewById(R.id.passwordText);


    }

    public void registerNew(View view) {
        Intent rIntent = new Intent(this, NewUser.class);
        startActivity(rIntent);
        finish();
    }



    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

    }

    public void exit(View view) {
        confirmLogOutDialog();
    }

    //deletes all data from database
    public void nuketable(View view) {
        confirmDeleteDialog();
    }

    //checks the input to see if user is registered
    private void confirmDeleteDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete Action!");
        builder.setMessage("You are about to delete all records of database. Do you really want to proceed ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've choosen to delete all records", Toast.LENGTH_SHORT).show();
                    deleteUserTask dUser = new deleteUserTask();
                    dUser.execute();
                    Toast.makeText(getBaseContext(), "All Data Deleted!!", Toast.LENGTH_SHORT).show();
                }

        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've changed your mind to delete all records", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    private void confirmLogOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Log Out Action");
        builder.setMessage("You are about to exit the app. Do you really want to proceed ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've changed your mind to logged out", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

 private class deleteUserTask extends AsyncTask<Void, Void, Void> {
        private AppDatabase db;


        @Override
        protected Void doInBackground(Void... params) {

            db = AppDatabase.getDatabase(MainActivity.this);
           db.clearAllTables();
            return null;
        }
    }
    private class getOldUserCallable implements Callable<List<User>>

       {
           List<User> rList;
            @Override
            public List<User> call(){
                AppDatabase app = AppDatabase.getDatabase(MainActivity.this);
                rList = app.userDao().getAnyUser();
                return rList;

            }
        }

private class getOldUserFuture {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    getOldUserCallable newC = new getOldUserCallable();

    private getOldUserFuture() throws ExecutionException, InterruptedException {
    }

    Future<List<User>> future = executorService.submit(newC);
    List<User> result = future.get();
}
public void checkOldUserFuture (View view) throws ExecutionException, InterruptedException {
    oldUser = user.getText().toString();
    oldPass = pass.getText().toString();

         List<User> oldUserList;
         getOldUserFuture oFuture = new getOldUserFuture();
         oldUserList = oFuture.result;
         if(oldUserList.isEmpty())
         {
             Toast.makeText(getApplicationContext(),"No registered user",Toast.LENGTH_SHORT).show();
         }
         else if(oldUserList.equals(null))
         {
        Toast.makeText(getApplicationContext(),"Coming uo with nothing!",Toast.LENGTH_SHORT).show();
         }
         if(!oldUserList.isEmpty()){
             for(User user : oldUserList){
                 oldU = user.getUser();
                 oldP = user.getPassword();
             }
             if(oldU.equals(oldUser)&& oldP.equals(oldPass)){
                 Intent Main = new Intent(this, MainPage.class);
                     startActivity(Main);
                     finish();
                     Toast.makeText(getBaseContext(), "Welcome " + oldUser, Toast.LENGTH_SHORT).show();
                 }
             else if(!oldU.equals(oldUser)|| !oldP.equals(oldPass))
             {
                 Toast.makeText(getApplicationContext(),"Wrong details entered! Try Again!",Toast.LENGTH_SHORT).show();
             }

             }
         }

}





