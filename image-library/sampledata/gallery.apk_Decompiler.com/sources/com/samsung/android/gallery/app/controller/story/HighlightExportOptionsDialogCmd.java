package com.samsung.android.gallery.app.controller.story;

import U3.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightExportOptionsDialogCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public void updateCustomRatio(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            Log.e(this.TAG, "HighlightExportOptionsDialogCmd invalid data");
        } else {
            getBlackboard().postEvent(EventMessage.obtain(1123, arrayList.get(0)));
        }
    }

    public String getEventId() {
        return null;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/HighlightExportOptions").appendArg("index", objArr[0].intValue()).build()).setOnDataCompleteListener(new a(2, this)).start();
    }
}
