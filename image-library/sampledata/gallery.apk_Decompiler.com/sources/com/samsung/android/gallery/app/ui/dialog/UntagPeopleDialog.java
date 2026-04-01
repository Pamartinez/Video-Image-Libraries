package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import q4.D;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UntagPeopleDialog extends BaseDialog {
    private void dismissWithLog() {
        Log.d(this.TAG, "The name is null");
        dismiss();
    }

    private String getName() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments.getString("name");
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void onClickNegative(DialogInterface dialogInterface, int i2) {
        publishCancel();
    }

    /* access modifiers changed from: private */
    public void onClickPositive(DialogInterface dialogInterface, int i2) {
        publishData(String.valueOf(i2));
        dismiss();
    }

    private void publishCancel() {
        publishInternal((String) null);
    }

    private void publishData(String str) {
        publishInternal(str);
    }

    private void publishInternal(String str) {
        AnalyticsEventId analyticsEventId;
        getBlackboard().post("data://user/dialog/RemovePeopleTag", str);
        if (str == null) {
            analyticsEventId = AnalyticsEventId.EVENT_PEOPLE_TAG_REMOVE_DIALOG_CANCEL_CLICKED;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_PEOPLE_TAG_REMOVE_DIALOG_REMOVE_CLICKED;
        }
        postAnalyticsLog(analyticsEventId);
    }

    public Dialog createDialog(Bundle bundle) {
        String name = getName();
        if (name == null) {
            dismissWithLog();
        }
        return new AlertDialog.Builder(getContext()).setTitle((int) R.string.remove_tag_dialog_title).setMessage((CharSequence) getString(R.string.remove_tag_dialog_body, name)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new D(this, 0)).setPositiveButton((int) R.string.remove, (DialogInterface.OnClickListener) new D(this, 1)).create();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_DETAIL_VIEW_PICTURE.toString();
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/RemovePeopleTag", (Object) null);
    }
}
