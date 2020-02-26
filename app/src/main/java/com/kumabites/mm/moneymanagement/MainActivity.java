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
    public void setList(List<User> list) {
        this.findAnyResult = list;
    }
    public void registerNew(View view) {
        Intent rIntent = new Intent(this, NewUser.class);
        startActivity(rIntent);
        finish();
    }

    public String getOUser(List<User> oldUserList) {

        for (User user : oldUserList) {
            userid = user.getUser();
            return userid;
        }
        return null;
    }

    public String getOPass(List<User> oldUserList) {

        for (User user : oldUserList) {
            passid = user.getPassword();
            return passid;
        }
        return null;
    }

    public void userCheck(String oldUser, String newUser, String oldPass, String newPass) {
        Intent Main = new Intent(this, MainPage.class);
        if (oldUser == null || oldPass == null) {
            user.setText("");
            pass.setText("");
            Toast.makeText(getBaseContext(), "Wrong details", Toast.LENGTH_SHORT).show();
        } else if (oldUser.equals(newUser) && oldPass.equals(newPass)) {
            CurrentUser.setUsername(oldUser);
            startActivity(Main);
            finish();
            Toast.makeText(getBaseContext(), "Welcome " + oldUser, Toast.LENGTH_SHORT).show();
        } else {
            user.setText("");
            pass.setText("");
            Toast.makeText(getBaseContext(), "Wrong details", Toast.LENGTH_SHORT).show();
        }

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
    public void oldUser(View view) {
        oldUser = user.getText().toString();
        oldPass = pass.getText().toString();
        List<User> getOldUser = appDatabase.userDao().findUser(oldUser, oldPass);
        oldU = getOUser(getOldUser);
        oldP = getOPass(getOldUser);
        userCheck(oldU, oldUser, oldP, oldPass);


    }

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
//Supposed to run the check in the background and update the List<User> but I am getting null

 private class deleteUserTask extends AsyncTask<Void, Void, Void> {
        private AppDatabase db;


        @Override
        protected Void doInBackground(Void... params) {

            db = AppDatabase.getDatabase(MainActivity.this);
           db.clearAllTables();
            return null;
        }
    }
    private class Test implements Callable<List<User>>

       {
           List<User> rList;
            @Override
            public List<User> call() throws Exception{
                AppDatabase app = AppDatabase.getDatabase(MainActivity.this);
                rList = app.userDao().getAnyUser();
                return rList;

            }
        }

private class TestFuture {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Test newC = new Test();

    private TestFuture() throws ExecutionException, InterruptedException {
    }

    Future<List<User>> future = executorService.submit(newC);
    List<User> result = future.get();
}
public void testList (View view) throws ExecutionException, InterruptedException {
         List<User> testResp;
         TestFuture nwTest = new TestFuture();
         testResp = nwTest.result;
         if(testResp.isEmpty())
         {Toast.makeText(getApplicationContext(),"list is empty",Toast.LENGTH_SHORT).show();}
         if(!testResp.isEmpty()){
             Toast.makeText(getApplicationContext(),"list isn't empty",Toast.LENGTH_SHORT).show();
         }

}


}


