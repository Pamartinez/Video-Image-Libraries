package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataList;
import com.samsung.android.gallery.support.providers.GmpCompat;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class E implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2943a;
    public final /* synthetic */ MediaDataRef b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2944c;

    public /* synthetic */ E(MediaDataRef mediaDataRef, Object obj, int i2) {
        this.f2943a = i2;
        this.b = mediaDataRef;
        this.f2944c = obj;
    }

    public final boolean test(Object obj) {
        switch (this.f2943a) {
            case 0:
                return ((MediaDataList.MediaDataArray) this.b).lambda$notifyDataChangedGmp$0((GmpCompat.GmpDataChangeDetails) this.f2944c, (MediaItem) obj);
            default:
                return ((MediaDataSearchStories) this.b).lambda$updateChildData$1((String) this.f2944c, (MediaItem) obj);
        }
    }
}
