package com.kumabites.mm.moneymanagement;


import android.content.Context;

import MMDAO.debtDao;
import MMDAO.userDao;
import MMENTITY.Debt;
import MMENTITY.User;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Debt.class, User.class}, version = 2, exportSchema =  false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract debtDao debtDao();
    public abstract userDao userDao();
    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "userdb")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
