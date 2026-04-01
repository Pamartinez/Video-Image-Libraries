package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaDataRemasterV2;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Z implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2963a;

    public /* synthetic */ Z(int i2) {
        this.f2963a = i2;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2963a) {
            case 0:
                ((MediaDataRemasterV2.MediaDataChild) obj2).onDestroy();
                return;
            case 1:
                ((MediaDataRemasterV2.MediaDataChild) obj2).onCreate();
                return;
            default:
                ((MediaData) obj2).recall(true);
                return;
        }
    }
}
