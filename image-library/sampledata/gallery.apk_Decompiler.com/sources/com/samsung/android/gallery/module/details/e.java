package com.samsung.android.gallery.module.details;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3006a;
    public final /* synthetic */ Cloneable b;

    public /* synthetic */ e(Cloneable cloneable, int i2) {
        this.f3006a = i2;
        this.b = cloneable;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3006a;
        Cloneable cloneable = this.b;
        switch (i2) {
            case 0:
                return DetailsUtil.lambda$removeDuplicated$0((MediaItem) cloneable, (MediaItem) obj);
            case 1:
                return EditDetailsUpdater.lambda$updateGroupShotDateTime$5((MediaItem) cloneable, (MediaItem) obj);
            default:
                return ((ArrayList) cloneable).stream().anyMatch(new e((MediaItem) obj, 0));
        }
    }
}
