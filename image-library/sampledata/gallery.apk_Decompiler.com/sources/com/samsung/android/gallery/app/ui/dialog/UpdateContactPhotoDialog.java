package com.samsung.android.gallery.app.ui.dialog;

import C3.g;
import Fa.C0558l;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UpdateContactPhotoDialog extends BaseDialog {
    private boolean mFromSearch;
    private String mScreenId;

    private void dismissWithLog() {
        Log.d(this.TAG, "The name is null");
        dismiss();
    }

    private String getTargetName() {
        if (getArguments() != null) {
            return getArguments().getString("name");
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(DialogInterface dialogInterface, int i2) {
        publishCancel();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$1(String str, DialogInterface dialogInterface, int i2) {
        onClickPositive(str);
    }

    private void loadArguments(Bundle bundle) {
        if (bundle != null) {
            this.mScreenId = bundle.getString("screenId", (String) null);
            if (AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_FACE_TAG.toString().equals(this.mScreenId)) {
                this.mScreenId = AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_FACE_SUGGESTION_LIST.toString();
                this.mFromSearch = true;
            }
        }
    }

    private void onClickPositive(String str) {
        AnalyticsEventId analyticsEventId;
        if (this.mFromSearch) {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_CONTACT_UPDATE_OK;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_PEOPLE_TAG_CONTACT_UPDATE_OK;
        }
        postAnalyticsLog(analyticsEventId);
        publishData(str);
        dismiss();
    }

    private void publishCancel() {
        AnalyticsEventId analyticsEventId;
        if (this.mFromSearch) {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_CONTACT_UPDATE_CANCEL;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_PEOPLE_TAG_CONTACT_UPDATE_CANCEL;
        }
        postAnalyticsLog(analyticsEventId);
        publishInternal((String) null);
    }

    private void publishData(String str) {
        publishInternal(str);
    }

    private void publishInternal(String str) {
        getBlackboard().post("data://user/dialog/UpdateContactPhoto", str);
    }

    public Dialog createDialog(Bundle bundle) {
        String targetName = getTargetName();
        if (targetName == null) {
            dismissWithLog();
        }
        loadArguments(getArguments());
        return new AlertDialog.Builder(getContext()).setTitle((CharSequence) getString(R.string.header_confirm_dialog_update_contact, targetName)).setMessage((CharSequence) getString(R.string.body_confirm_dialog_update_contact, targetName)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new C0558l(17, this)).setPositiveButton((int) R.string.add, (DialogInterface.OnClickListener) new g(6, this, targetName)).create();
    }

    public String getScreenId() {
        return this.mScreenId;
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/UpdateContactPhoto", (Object) null);
    }
}
