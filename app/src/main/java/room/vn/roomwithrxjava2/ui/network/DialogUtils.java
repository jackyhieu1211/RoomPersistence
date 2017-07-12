package room.vn.roomwithrxjava2.ui.network;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtils {

    private static DialogUtils instance;

    private DialogUtils() {
    }

    public static DialogUtils getInstance() {
        if (instance == null) {
            instance = new DialogUtils();
        }
        return instance;
    }


    public ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
