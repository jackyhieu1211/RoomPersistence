package room.vn.roomwithrxjava2.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import room.vn.roomwithrxjava2.R;
import room.vn.roomwithrxjava2.api.ApiManager;
import room.vn.roomwithrxjava2.api.UserApiService;
import room.vn.roomwithrxjava2.room.AppDatabase;
import room.vn.roomwithrxjava2.room.model.Room_User;
import room.vn.roomwithrxjava2.ui.network.RxNetwork;
import room.vn.roomwithrxjava2.ui.network.RxShowDialogUtil;
import timber.log.Timber;

public class MainActivity extends RxAppCompatActivity {
    AppDatabase appDatabase;
    List<Room_User> room_users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        appDatabase = AppDatabase.getAppDatabase(this);
    }

    @OnClick(R.id.text_get_api)
    public void onViewClicked() {
        getApi();
    }

    private void getApi() {
        RxNetwork.checkNetwork(this).flatMap(connect -> ApiManager.createApiService(UserApiService.class).getAllInfo("jackyhieu1211"))
                .subscribeOn(Schedulers.io())
                .compose(RxShowDialogUtil.createInstance(this).applyDialogForFlowable())
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    Room_User room_user = new Room_User();
                    room_user.login = user.login;
                    room_user.id_user = user.id;
                    room_user.avatarUrl = user.avatarUrl;
                    room_user.createdAt = user.createdAt;
                    room_user.updatedAt = user.updatedAt;
                    room_users.clear();
                    room_users.add(room_user);
                    appDatabase.roomDb().insert(room_users);
                    startActivity(new Intent(MainActivity.this, TestActivity.class));
                }, throwable -> {
                    Timber.e(throwable.getMessage());
                    // ToastUtil.getInstance().showToast(this, throwable.getMessage());
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }

}
