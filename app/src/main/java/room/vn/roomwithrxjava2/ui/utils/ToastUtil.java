package room.vn.roomwithrxjava2.ui.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jackyhieu1211 on 7/12/17.
 */

public class ToastUtil {

    private static ToastUtil sToastUtil;

    public static ToastUtil getInstance() {
        if (sToastUtil == null) {
            sToastUtil = new ToastUtil();
        }
        return sToastUtil;
    }

    private static Toast sToast;

    public void showToast(Context context, String message) {
        if (sToast != null) sToast.cancel();
        sToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        sToast.show();
    }

    public void showToast(Context context, int message) {
        if (sToast != null) sToast.cancel();
        sToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        sToast.show();
    }
}
