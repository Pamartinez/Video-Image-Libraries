package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import q4.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EraseObjectDialog extends BaseDialog {
    private static final int[] OPTIONS = {R.string.save_as_image, R.string.save_as_video, R.string.cancel};

    private View inflateView(Context context) {
        ScrollView scrollView = (ScrollView) LayoutInflater.from(context).inflate(R.layout.create_new_dialog_container, (ViewGroup) null);
        ViewGroup viewGroup = (ViewGroup) scrollView.findViewById(R.id.dialog_container);
        int i2 = 0;
        while (true) {
            int[] iArr = OPTIONS;
            if (i2 >= iArr.length) {
                return scrollView;
            }
            View inflate = LayoutInflater.from(context).inflate(R.layout.erase_object_dialog_item, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.title)).setText(iArr[i2]);
            inflate.setOnClickListener(new e(this, i2, 1));
            inflate.setVisibility(0);
            viewGroup.addView(inflate);
            i2++;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateView$0(int i2, View view) {
        getBlackboard().post("data://user/dialog/EraseObject", Integer.valueOf(i2));
        dismiss();
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context != null) {
            return new AlertDialog.Builder(context).setTitle((int) R.string.save_result_as_image_or_video_title).setMessage((int) R.string.save_result_as_image_or_video_message).setView(inflateView(context)).create();
        }
        Log.e(this.TAG, "createDialog failed, null context");
        return super.createDialog(bundle);
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/EraseObject", (Object) null);
        super.onCancel(dialogInterface);
    }
}
