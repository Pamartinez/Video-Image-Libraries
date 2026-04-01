package com.samsung.android.gallery.app.controller.viewer;

import U3.a;
import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditDetailsSaveOrDiscardCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public void onDataComplete(EventContext eventContext, ArrayList<Object> arrayList) {
        Blackboard blackboard = getBlackboard();
        if (arrayList == null || arrayList.isEmpty()) {
            Log.d(this.TAG, "SaveDetails : do nothing");
            postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_DIALOG_CANCEL);
            return;
        }
        try {
            int intValue = ((Integer) arrayList.get(0)).intValue();
            if (intValue == 1) {
                Log.d(this.TAG, "SaveEditDetails : save");
                blackboard.postEvent(EventMessage.obtain(3006));
                postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_DIALOG_SAVE);
            } else if (intValue == 2) {
                Log.d(this.TAG, "SaveEditDetails : not save");
                blackboard.postEvent(EventMessage.obtain(3007));
                postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_DIALOG_DISCARD);
            } else {
                Log.e(this.TAG, "SaveEditDetails : Invalid");
            }
        } catch (ClassCastException e) {
            String str = this.TAG;
            Log.e(str, "unexpected result delivered." + e.getMessage());
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Context context = getContext();
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("description", context.getString(R.string.save_or_discard_change)).appendArg("option1", context.getString(R.string.save)).appendArg("option2", context.getString(R.string.crop_back_key_confirm_dialog_discard)).build()).setOnDataCompleteListener(new a(14, this)).start();
    }
}
