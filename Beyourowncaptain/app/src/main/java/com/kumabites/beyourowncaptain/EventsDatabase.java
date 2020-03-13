package com.kumabites.beyourowncaptain;

import android.content.Context;

import com.kumabites.beyourowncaptain.DAO.EventsDao;
import com.kumabites.beyourowncaptain.ENTITY.Events;

import java.io.File;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Events.class}, version = 1, exportSchema =  false)
public abstract class EventsDatabase extends RoomDatabase {


    public abstract EventsDao eventsDao();
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
