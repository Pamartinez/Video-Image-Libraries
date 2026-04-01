package com.samsung.android.gallery.database.dal.local;

import com.samsung.android.gallery.database.dal.local.AlbumGroupView;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2625a;

    public /* synthetic */ f(int i2) {
        this.f2625a = i2;
    }

    public final Object apply(Object obj) {
        AlbumGroupView.Group group = (AlbumGroupView.Group) obj;
        switch (this.f2625a) {
            case 0:
                return group.albumIds;
            default:
                return String.valueOf(group.bucketId);
        }
    }
}
