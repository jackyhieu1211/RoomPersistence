package seesaa.vn.roomwithrxjava2.room.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = UserConstant.HISTORY_TABLE)
public class Room_User {
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo(name = UserConstant.COL_ID)
    public Integer id_user;
    @ColumnInfo(name = UserConstant.COL_LOGIN)
    public String login;
    @ColumnInfo(name = UserConstant.COL_AVATAR_URL)
    public String avatarUrl;
    @ColumnInfo(name = UserConstant.COL_CREATE_AT)
    public String createdAt;
    @ColumnInfo(name = UserConstant.COL_UPDATE_AT)
    public String updatedAt;

}
