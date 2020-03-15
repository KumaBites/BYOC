package com.kumabites.beyourowncaptain;

import android.content.Context;

import com.kumabites.beyourowncaptain.DAO.EventsDao;
import com.kumabites.beyourowncaptain.DAO.Fantasy_Enemy_Dao;
import com.kumabites.beyourowncaptain.DAO.Fantasy_Events_Dao;
import com.kumabites.beyourowncaptain.ENTITY.Events;
import com.kumabites.beyourowncaptain.ENTITY.Fantasy_Enemy;
import com.kumabites.beyourowncaptain.ENTITY.Fantasy_Events;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Events.class, Fantasy_Enemy.class, Fantasy_Events.class}, version = 1, exportSchema =  false)
public abstract class EventsDatabase extends RoomDatabase {


    public abstract EventsDao eventsDao();
    public abstract Fantasy_Events_Dao fantasyDao();
    public abstract Fantasy_Enemy_Dao fantasyEnemyDao();
    private static volatile EventsDatabase INSTANCE;

    public static EventsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EventsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EventsDatabase.class, "eventsdb")
                            .createFromAsset("Events.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
                }
        }
        return INSTANCE;
    }
}
