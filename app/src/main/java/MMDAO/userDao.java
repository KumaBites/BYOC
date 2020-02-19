package MMDAO;

import java.util.List;

import MMENTITY.User;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface userDao {

//searches for the user and password for login
@Query("select * from user_table where user LIKE :user AND password LIKE :password")
List<User> findUser(String user, String password);

//Registering new user
@Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

//Deleting current user
@Delete
    void deleteUser(User user);

@Query("SELECT * from user_table ORDER BY user ASC")
List<User> getAnyUser();

@Query("select * from user_table where user LIKE :user")
List<User> findUserSingle(String user);
}
