package seesaa.vn.roomwithrxjava2.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import seesaa.vn.roomwithrxjava2.R;
import seesaa.vn.roomwithrxjava2.room.AppDatabase;
import seesaa.vn.roomwithrxjava2.room.model.Room_User;
import seesaa.vn.roomwithrxjava2.ui.adapter.TestAdapter;
import seesaa.vn.roomwithrxjava2.ui.network.RxNetwork;
import seesaa.vn.roomwithrxjava2.ui.utils.DividerItemDecoration;
import seesaa.vn.roomwithrxjava2.ui.utils.ToastUtil;
import timber.log.Timber;

public class TestActivity extends RxAppCompatActivity implements TestAdapter.OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<Room_User> roomUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider_recycler_view), true, true));
        TestAdapter testAdapter = new TestAdapter(roomUsers, this);
        recyclerView.setAdapter(testAdapter);

        AppDatabase.getAppDatabase(this).roomDb().getAllHistoryByTime()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(room_users -> {
                    roomUsers.clear();
                    roomUsers.addAll(room_users);
                    testAdapter.notifyDataSetChanged();
                }, throwable -> {
                    Timber.e(throwable.getMessage());
                });
    }

    @Override

    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }

    @Override
    public void onItemClick(Room_User roomUser) {
        ToastUtil.getInstance().showToast(this, roomUser.login);
    }
}
