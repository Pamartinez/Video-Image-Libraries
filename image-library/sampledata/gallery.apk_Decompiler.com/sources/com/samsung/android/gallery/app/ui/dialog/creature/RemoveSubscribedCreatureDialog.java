package com.samsung.android.gallery.app.ui.dialog.creature;

import A.a;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import s4.C0514a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveSubscribedCreatureDialog extends BaseDialog {
    private void dismissDialog() {
        try {
            dismissAllowingStateLoss();
        } catch (Exception e) {
            a.s(e, new StringBuilder("dismiss dialog failed e="), this.TAG);
        }
    }

    private String getUnsubscribePersonMessage(Context context) {
        if (Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM)) {
            return context.getResources().getString(R.string.remove_subscribed_creature_in_auto_updating_album);
        }
        return context.getString(R.string.remove_subscribed_person_in_auto_updating_album);
    }

    private String getUnsubscribePersonTitle(Context context, String str) {
        if (Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM)) {
            return context.getResources().getString(R.string.remove_creature_from_auto_updating_album);
        }
        if (TextUtils.isEmpty(str)) {
            return context.getResources().getString(R.string.remove_person_without_name_from_auto_updating_album);
        }
        return context.getResources().getString(R.string.remove_person_with_name_from_auto_updating_album, new Object[]{str});
    }

    /* access modifiers changed from: private */
    public void onClickNegative(DialogInterface dialogInterface, int i2) {
        postAnalyticsLog(AnalyticsEventId.EVENT_REMOVE_SUBSCRIBED_PERSON_DIALOG_CANCEL);
        dismissDialog();
    }

    /* access modifiers changed from: private */
    public void onClickPositive(DialogInterface dialogInterface, int i2) {
        postAnalyticsLog(AnalyticsEventId.EVENT_REMOVE_SUBSCRIBED_PERSON_DIALOG_OK);
        getBlackboard().post("data://user/dialog/UnsubscribeCreature", Boolean.TRUE);
        dismissDialog();
    }

    public Dialog createDialog(Bundle bundle) {
        int i2;
        Context context = getContext();
        if (context == null) {
            return super.createDialog(bundle);
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            AlertDialog.Builder message = new AlertDialog.Builder(context).setTitle((CharSequence) getUnsubscribePersonTitle(context, arguments.getString("name", ""))).setMessage((CharSequence) getUnsubscribePersonMessage(context));
            if (Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM)) {
                i2 = R.string.stop;
            } else {
                i2 = R.string.remove;
            }
            return message.setPositiveButton(i2, (DialogInterface.OnClickListener) new C0514a(this, 0)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new C0514a(this, 1)).create();
        }
        throw new IllegalArgumentException("bundle is null");
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/UnsubscribeCreature", (Object) null);
    }
}
