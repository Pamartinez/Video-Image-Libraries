package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements DataCollectionDelegate.OnDataCompletionListener {
    public final void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        Blackboard.postBroadcastEventGlobal(EventMessage.obtain(3001));
    }
}
