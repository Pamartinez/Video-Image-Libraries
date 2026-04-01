package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.view.View;
import android.widget.AdapterView;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.OverflowPanel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements AdapterView.OnItemClickListener {
    public final /* synthetic */ ObjectCapturePopup d;
    public final /* synthetic */ OverflowPanel e;

    public /* synthetic */ b(ObjectCapturePopup objectCapturePopup, OverflowPanel overflowPanel) {
        this.d = objectCapturePopup;
        this.e = overflowPanel;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        ObjectCapturePopup.createOverflowPanel$lambda$5$lambda$4(this.d, this.e, adapterView, view, i2, j2);
    }
}
