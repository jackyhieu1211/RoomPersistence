package room.vn.roomwithrxjava2;

import android.app.Application;

import room.vn.roomwithrxjava2.ui.timber.ExtDebugTree;
import timber.log.Timber;


public class RoomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new ExtDebugTree());
        }
    }
}
