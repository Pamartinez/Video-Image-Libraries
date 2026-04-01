package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ EventContext d;

    public /* synthetic */ c(EventContext eventContext) {
        this.d = eventContext;
    }

    public final void run() {
        this.d.getBlackboard().postEvent(EventMessage.obtain(1003));
    }
}
