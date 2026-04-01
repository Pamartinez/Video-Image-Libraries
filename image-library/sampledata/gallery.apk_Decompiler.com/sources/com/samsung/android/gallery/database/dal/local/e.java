package com.samsung.android.gallery.database.dal.local;

import com.samsung.android.gallery.database.dal.local.AlbumGroupView;
import java.util.HashMap;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AlbumGroupView.Group f2624a;
    public final /* synthetic */ HashMap b;

    public /* synthetic */ e(HashMap hashMap, AlbumGroupView.Group group) {
        this.f2624a = group;
        this.b = hashMap;
    }

    public final void accept(int i2) {
        this.f2624a.child.add((AlbumGroupView.Group) this.b.get(Integer.valueOf(i2)));
    }
}
