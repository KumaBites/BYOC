package MMENTITY;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    @NonNull
    private String user;

    private String password;

    public String getUser(){
        return user;
    }
    public void setUser( String user) {this.user = user;}
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {this.password = password;}





}
