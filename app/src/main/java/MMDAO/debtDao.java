package MMDAO;

import java.util.List;

import MMENTITY.Debt;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface debtDao {

    @Query("UPDATE debt_table SET remaining= :remaining AND  amount_paid= :amount_p where debt_name LIKE :debt")
    void newUpdate(int remaining,int amount_p,String debt);

    @Query("SELECT * FROM debt_table where debt_name LIKE :debt")
    List<Debt>getSingleDebt(String debt);


@Query("SELECT * FROM debt_table where user_name LIKE :user ")
    List<Debt>getAll(String user);

    @Query("DELETE FROM debt_table where debt_name LIKE :debt")
    void deleteOne(String debt);

@Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNew(Debt debt);

@Delete
    void deleteDebt(Debt debt);

@Delete
    void deleteAll(Debt...debts);

@Update
    void updateDebt(Debt...debt);

//use oid clearAllTables() to wipe tables in database
@Query("SELECT * from debt_table ORDER BY debt_name ASC")
   List<Debt>getAllDebt();

    @Query("SELECT * from debt_table where debt_name LIKE :OldDebtName")
    List<Debt>getDebt(String OldDebtName);


}
