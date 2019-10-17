package com.kumabites.mm.moneymanagement;


import MMDAO.debtDao;
import MMDAO.userDao;
import MMENTITY.Debt;
import MMENTITY.User;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Debt.class, User.class}, version = 2, exportSchema =  false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract debtDao debtDao();
    public abstract userDao userDao();


         }