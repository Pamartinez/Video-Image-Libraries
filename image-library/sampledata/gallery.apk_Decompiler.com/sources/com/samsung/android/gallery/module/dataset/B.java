package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaDataFilter;
import com.samsung.android.gallery.module.dataset.MediaDataRemasterV2;
import com.samsung.android.gallery.module.dataset.MediaDataSearch;
import com.samsung.android.gallery.module.dataset.MediaDataSearchScreenshotPictures;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ B(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MediaDataFilter.MediaDataFilteredList) this.e).lambda$filterMediaItems$0((ArrayList) this.f);
                return;
            case 1:
                ((MediaDataRemasterV2) this.e).lambda$loadFirst$7((MediaDataRemasterV2.DataInfo) this.f);
                return;
            case 2:
                ((MediaDataSearch.AnonymousClass1) this.e).lambda$onDataChanged$0((String) this.f);
                return;
            case 3:
                ((MediaDataSearchScreenshotPictures.SubSceneData) this.e).lambda$swap$0((ArrayList) this.f);
                return;
            case 4:
                ((MediaDataTimeline) this.e).lambda$notifySwapDone$8((List) this.f);
                return;
            case 5:
                ((MediaDataScreenShotFilter) this.e).lambda$swap$1((ArrayList) this.f);
                return;
            default:
                ((MediaDataSearchStories) this.e).lambda$swapData$0((ArrayList) this.f);
                return;
        }
    }
}
