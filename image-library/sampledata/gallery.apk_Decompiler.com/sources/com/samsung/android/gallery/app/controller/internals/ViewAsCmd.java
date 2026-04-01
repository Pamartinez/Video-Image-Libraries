package com.samsung.android.gallery.app.controller.internals;

import O3.y;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewAsCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public void changeViewMode(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            Integer num = (Integer) arrayList.get(0);
            num.intValue();
            getBlackboard().postEvent(EventMessage.obtain(1024, num));
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/ViewAs").appendArg("locationKey", eventContext.getLocationKey()).appendArg("current_depth", eventContext.getCurrentViewDepth()).appendArg("max_depth", eventContext.getMaxDepth()).build()).setOnDataCompleteListener(new y(2, this)).start();
    }
}
