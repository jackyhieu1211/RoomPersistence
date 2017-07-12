package room.vn.roomwithrxjava2.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("id")
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
