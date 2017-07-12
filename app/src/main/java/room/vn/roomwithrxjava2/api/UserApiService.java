package room.vn.roomwithrxjava2.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import room.vn.roomwithrxjava2.api.model.User;

public interface UserApiService {
    @GET("/users/{name}")
    Flowable<User> getAllInfo(@Path("name") String name);
}
