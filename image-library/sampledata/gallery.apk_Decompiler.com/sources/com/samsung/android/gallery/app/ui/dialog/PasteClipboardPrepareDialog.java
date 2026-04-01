package com.samsung.android.gallery.app.ui.dialog;

import Fa.C0558l;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.module.clipboard.ClipboardDataPrepareTask;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PasteClipboardPrepareDialog extends BaseDialog {
    private ClipboardDataPrepareTask mTask;

    private void dismissDialog() {
        try {
            dismissAllowingStateLoss();
        } catch (Exception unused) {
            Log.w(this.TAG, "fail dismiss");
        }
    }

    private View getCustomView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.progress_circle_dialog, (ViewGroup) null, false);
        inflateProgressBar(inflate);
        return inflate;
    }

    private void inflateProgressBar(View view) {
        ViewUtils.inflateViewStub(view.findViewById(R.id.progress_circle));
    }

    /* access modifiers changed from: private */
    public void onCancelClicked(DialogInterface dialogInterface, int i2) {
        Log.d(this.TAG, "cancelled");
        this.mTask.setInterrupt();
        getBlackboard().post("data://user/dialog/PastePrepare", (Object) null);
        dismissDialog();
    }

    /* access modifiers changed from: private */
    public void onPrepared(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2, byte b, int i2, String str) {
        Log.d(this.TAG, "onPrepared");
        getBlackboard().post("data://user/dialog/PastePrepare", new Object[]{arrayList, arrayList2, Byte.valueOf(b), Integer.valueOf(i2), str});
        dismissDialog();
    }

    private void startTask(Context context) {
        ClipboardDataPrepareTask clipboardDataPrepareTask = new ClipboardDataPrepareTask(context, new p(2, this));
        this.mTask = clipboardDataPrepareTask;
        clipboardDataPrepareTask.run();
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context == null) {
            return super.createDialog(bundle);
        }
        startTask(context);
        return new AlertDialog.Builder(context).setTitle((int) R.string.checking_media_files_available_to_paste).setView(getCustomView(context)).setCancelable(false).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new C0558l(14, this)).create();
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/PastePrepare", (Object) null);
    }
}
