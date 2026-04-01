package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaDataList;
import com.samsung.android.gallery.module.dataset.MediaDataSearchStories;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ r(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((MediaDataCollection) obj).notifyChanged();
                return;
            case 1:
                ((MediaDataList.MediaDataArray) obj).notifyChanged();
                return;
            case 2:
                ((MediaDataSearchStories.AnonymousClass1) obj).lambda$onDataChanged$0();
                return;
            default:
                ((MediaDataTimeline2) obj).lambda$beginSwapProcessing$4();
                return;
        }
    }
}
