package com.samsung.android.gallery.database.dal.local;

import com.samsung.android.gallery.database.dal.local.AlbumGroupView;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2622a;
    public final /* synthetic */ Comparable b;

    public /* synthetic */ c(Comparable comparable, int i2) {
        this.f2622a = i2;
        this.b = comparable;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2622a;
        Comparable comparable = this.b;
        AlbumGroupView.Group group = (AlbumGroupView.Group) obj;
        switch (i2) {
            case 0:
                return AlbumGroupView.lambda$query$6(comparable, group);
            case 1:
                return AlbumGroupView.lambda$query$7(comparable, group);
            default:
                return AlbumGroupView.lambda$query$8(comparable, group);
        }
    }
}
