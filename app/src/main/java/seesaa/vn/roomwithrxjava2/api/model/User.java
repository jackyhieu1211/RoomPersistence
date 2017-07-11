package seesaa.vn.roomwithrxjava2.api.model;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import seesaa.vn.roomwithrxjava2.room.model.UserConstant;

public class User {
    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("id_user")
    @Expose
    public Integer id;
    @SerializedName("avatar_url")
    @Expose
    public String avatarUrl;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
}
