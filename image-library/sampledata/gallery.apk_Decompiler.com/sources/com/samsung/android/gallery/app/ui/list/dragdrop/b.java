package com.samsung.android.gallery.app.ui.list.dragdrop;

import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.dragdrop.NormalDragAndDrop;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements DataCollectionDelegate.OnDataCompletionListener {
    public final /* synthetic */ NormalDragAndDrop d;
    public final /* synthetic */ NormalDragAndDrop.OnDataCompletionListener e;

    public /* synthetic */ b(NormalDragAndDrop normalDragAndDrop, NormalDragAndDrop.OnDataCompletionListener onDataCompletionListener) {
        this.d = normalDragAndDrop;
        this.e = onDataCompletionListener;
    }

    public final void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        this.d.lambda$showConfirmDialog$3(this.e, eventContext, arrayList);
    }
}
