package seesaa.vn.roomwithrxjava2.ui.network;

import android.content.Context;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class RxNetwork {
    private RxNetwork() {
    }

    public static Flowable<Boolean> checkNetwork(final Context context) {
        return Flowable.create(subscriber -> {
            if (NetworkUtil.isNetworkAvailable(context)) {
                subscriber.onNext(true);
            } else {
                subscriber.onError(new NoNetworkException());
            }
        }, BackpressureStrategy.MISSING);
    }

    public static class NoNetworkException extends RuntimeException {
        public NoNetworkException() {
            super();
        }
    }

}
