package room.vn.roomwithrxjava2.ui.network;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class RxShowDialogUtil {

    private ProgressDialog dialog;
    private final Context context;

    private final Consumer SUBSCRIBE_ACTION = new Consumer() {
        @Override
        public void accept(@NonNull Object o) throws Exception {
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        }
    };

    private final Action UN_SUBSCRIBE_ACTION = new Action() {
        @Override
        public void run() throws Exception {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }
    };

    private RxShowDialogUtil(Context context) {
        this.context = context;
    }

    public static RxShowDialogUtil createInstance(Context context) {
        return new RxShowDialogUtil(context);
    }

    public static RxShowDialogUtil createInstance(Fragment fragment) {
        return new RxShowDialogUtil(fragment.getActivity());
    }

    public <T> SingleTransformer<T, T> applyDialogForSingle() {
        createDialog(context);
        return upstream -> upstream.doOnSubscribe(SUBSCRIBE_ACTION).doFinally(UN_SUBSCRIBE_ACTION);
    }

    public <T> ObservableTransformer<T, T> applyDialog() {
        createDialog(context);
        return upstream -> upstream.doOnSubscribe(SUBSCRIBE_ACTION).doOnTerminate(UN_SUBSCRIBE_ACTION);
    }

    public <T>FlowableTransformer<T,T> applyDialogForFlowable(){
        createDialog(context);
        return upstream -> upstream.doOnSubscribe(SUBSCRIBE_ACTION).doFinally(UN_SUBSCRIBE_ACTION);
    }

    private Dialog createDialog(final Context context) {
        if (dialog == null) {
            dialog = DialogUtils.getInstance().getProgressDialog(context);
        }
        return dialog;
    }
}
