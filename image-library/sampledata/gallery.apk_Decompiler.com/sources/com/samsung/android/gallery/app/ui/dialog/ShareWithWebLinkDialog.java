package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import q4.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareWithWebLinkDialog extends BaseDialog {
    private String mScreenId;

    private CharSequence[] getItemList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getContext().getString(R.string.image));
        arrayList.add(getContext().getString(R.string.web_link));
        return (CharSequence[]) arrayList.toArray(new CharSequence[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(DialogInterface dialogInterface, int i2) {
        onItemSelected(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$1(DialogInterface dialogInterface, int i2) {
        publishCancel();
    }

    private void loadArguments(Bundle bundle) {
        if (bundle != null) {
            this.mScreenId = bundle.getString("screenId", (String) null);
        }
    }

    private void onItemSelected(int i2) {
        String str;
        String str2;
        if (i2 == 0) {
            str = "image";
        } else {
            str = "web_link";
        }
        publishInternal(str);
        AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_SHARE_AS_IMAGE_OR_WEB_LINK;
        if (i2 == 0) {
            str2 = AnalyticsDetail.SHARED_AS_IMAGE.toString();
        } else {
            str2 = AnalyticsDetail.SHARED_AS_WEB_LINK.toString();
        }
        postAnalyticsLog(analyticsEventId, str2);
    }

    private void publishCancel() {
        publishInternal((String) null);
    }

    private void publishInternal(String str) {
        getBlackboard().post("data://user/dialog/ShareWithWebLink", str);
    }

    public Dialog createDialog(Bundle bundle) {
        loadArguments(getArguments());
        return new AlertDialog.Builder(getContext()).setTitle((int) R.string.share_screenshot_as).setItems(getItemList(), new q(this, 0)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new q(this, 1)).create();
    }

    public String getScreenId() {
        return this.mScreenId;
    }

    public void onCancel(DialogInterface dialogInterface) {
        publishInternal((String) null);
        getBlackboard().post("command://CancelDialog", (Object) null);
        super.onCancel(dialogInterface);
    }
}
