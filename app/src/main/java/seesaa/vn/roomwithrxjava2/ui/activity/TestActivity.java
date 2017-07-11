package seesaa.vn.roomwithrxjava2.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import seesaa.vn.roomwithrxjava2.R;
import seesaa.vn.roomwithrxjava2.room.AppDatabase;
import seesaa.vn.roomwithrxjava2.room.model.Room_User;
import timber.log.Timber;

public class TestActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        AppDatabase.getAppDatabase(this).roomDb().getAllHistoryByTime()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(room_users -> {
                   for(Room_User room_user : room_users){
                       Timber.e("AAAAA %s", room_user.avatarUrl);
                       Timber.e("AAAAA %s", room_user.id);
                   }
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
