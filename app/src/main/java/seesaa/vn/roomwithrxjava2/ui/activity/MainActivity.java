package seesaa.vn.roomwithrxjava2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;
import seesaa.vn.roomwithrxjava2.R;
import seesaa.vn.roomwithrxjava2.api.ApiManager;
import seesaa.vn.roomwithrxjava2.api.UserApiService;
import seesaa.vn.roomwithrxjava2.room.AppDatabase;
import seesaa.vn.roomwithrxjava2.room.model.Room_User;
import timber.log.Timber;

public class MainActivity extends RxAppCompatActivity {
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase = AppDatabase.getAppDatabase(this);
        AppCompatButton appCompatButton = (AppCompatButton) findViewById(R.id.text_get_api);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApi();
            }
        });
    }


    private void getApi() {
        List<Room_User> room_users = new ArrayList<>();
        ApiManager.createApiService(UserApiService.class).getAllInfo("jackyhieu1211")
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .subscribe(user -> {
                    Room_User room_user = new Room_User();
                    room_user.login = user.login;
                    room_user.id_user = user.id;
                    room_user.avatarUrl = user.avatarUrl;
                    room_user.createdAt = user.createdAt;
                    room_user.updatedAt = user.updatedAt;
                    room_users.add(room_user);
                    appDatabase.roomDb().insert(room_users);
                    startActivity(new Intent(MainActivity.this, TestActivity.class));
                }, throwable -> {
                    Timber.e(throwable.getMessage());
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }
}
