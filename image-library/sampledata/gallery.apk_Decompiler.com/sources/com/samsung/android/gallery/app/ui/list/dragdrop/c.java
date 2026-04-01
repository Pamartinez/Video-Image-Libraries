package com.samsung.android.gallery.app.ui.list.dragdrop;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.dragdrop.NormalDragAndDrop;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ NormalDragAndDrop d;
    public final /* synthetic */ NormalDragAndDrop.OnDataCompletionListener e;
    public final /* synthetic */ EventContext f;

    public /* synthetic */ c(NormalDragAndDrop normalDragAndDrop, NormalDragAndDrop.OnDataCompletionListener onDataCompletionListener, EventContext eventContext) {
        this.d = normalDragAndDrop;
        this.e = onDataCompletionListener;
        this.f = eventContext;
    }

    public final void run() {
        this.d.lambda$onDataComplete$4(this.e, this.f);
    }
}
