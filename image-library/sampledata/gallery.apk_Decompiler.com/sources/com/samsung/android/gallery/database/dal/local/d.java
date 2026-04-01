package com.samsung.android.gallery.database.dal.local;

import com.samsung.android.gallery.database.dal.local.AlbumGroupView;
import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AlbumGroupView.Group f2623a;

    public /* synthetic */ d(AlbumGroupView.Group group) {
        this.f2623a = group;
    }

    public final boolean test(int i2) {
        return AlbumGroupView.lambda$query$1(this.f2623a, i2);
    }
}
