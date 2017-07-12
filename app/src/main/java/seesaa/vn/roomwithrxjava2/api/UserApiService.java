package seesaa.vn.roomwithrxjava2.api;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import seesaa.vn.roomwithrxjava2.api.model.User;

public interface UserApiService {
    @GET("/users/{name}")
    Flowable<User> getAllInfo(@Path("name") String name);
}
