package room.vn.roomwithrxjava2.room;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import room.vn.roomwithrxjava2.room.model.Room_User;

@Dao
public interface Room_Db {

    @Query("SELECT * FROM user ORDER BY updated_at DESC")
    Flowable<List<Room_User>> getAllHistoryByTime();

    @Query("SELECT * FROM user")
    Flowable<List<Room_User>> getAll();

    @Insert
    void insert(List<Room_User> room_users);

    @Delete
    void delete(List<Room_User> room_users);

    @Update
    void update(List<Room_User> room_users);
}
