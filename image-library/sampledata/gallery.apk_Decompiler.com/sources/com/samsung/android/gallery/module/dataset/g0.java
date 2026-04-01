package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g0 implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2975a;
    public final /* synthetic */ Map b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2976c;

    public /* synthetic */ g0(Map map, Object obj, int i2) {
        this.f2975a = i2;
        this.b = map;
        this.f2976c = obj;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2975a) {
            case 0:
                MediaDataSearch.lambda$makeOrderedCategoryList$4(this.b, (ArrayList) this.f2976c, (Integer) obj, (String) obj2);
                return;
            default:
                MediaDataExpandedSearchPictures.lambda$buildTempDataMapInternalFromCategory$0((HashMap) this.b, (HashMap) this.f2976c, (Long) obj, (MediaItem) obj2);
                return;
        }
    }
}
