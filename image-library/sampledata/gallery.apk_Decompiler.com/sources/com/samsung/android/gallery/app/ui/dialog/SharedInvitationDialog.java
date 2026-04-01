package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import q4.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedInvitationDialog extends BaseDialog {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(DialogInterface dialogInterface, int i2) {
        onClick(RequestCmdType.REQUEST_INVITATION_REJECTION);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$1(DialogInterface dialogInterface, int i2) {
        onClick(RequestCmdType.REQUEST_INVITATION_ACCEPTANCE);
    }

    private void onClick(RequestCmdType requestCmdType) {
        String str;
        if (RequestCmdType.REQUEST_INVITATION_ACCEPTANCE.equals(requestCmdType)) {
            str = AnalyticsEventId.EVENT_SHARED_VIEW_INVITATIONS_ACCEPT.toString();
        } else {
            str = AnalyticsEventId.EVENT_SHARED_VIEW_INVITATIONS_DECLINE.toString();
        }
        AnalyticsLogger.getInstance().postLog(getScreenId(), str);
        getBlackboard().post("data://user/dialog/SharedInvitation", requestCmdType);
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context == null) {
            Log.e(this.TAG, "createDialog failed null context");
            return super.createDialog(bundle);
        }
        String string = getString(R.string.shared_album_lc);
        Bundle arguments = getArguments();
        if (arguments != null) {
            string = BundleWrapper.getString(arguments, "space_name", string);
        }
        return new AlertDialog.Builder(context).setTitle((CharSequence) getString(R.string.shared_album_invitation_join_dialog_title, string)).setMessage((CharSequence) getString(R.string.your_profile_and_name_will_be_shared, string)).setNegativeButton((int) R.string.join_decline, (DialogInterface.OnClickListener) new r(this, 0)).setPositiveButton((int) R.string.join, (DialogInterface.OnClickListener) new r(this, 1)).create();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SHARED_VIEW_INVITATIONS.toString();
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/SharedInvitation", (Object) null);
    }
}
