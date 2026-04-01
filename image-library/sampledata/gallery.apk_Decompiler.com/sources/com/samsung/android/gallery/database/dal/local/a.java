package com.samsung.android.gallery.database.dal.local;

import com.samsung.android.gallery.database.dal.local.AlbumGroupView;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2621a;

    public /* synthetic */ a(int i2) {
        this.f2621a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2621a) {
            case 0:
                return AlbumGroupView.lambda$query$0((AlbumGroupView.Group) obj);
            case 1:
                return AlbumGroupView.Group.lambda$listOf$1((String) obj);
            case 2:
                return AlbumGroupView.Group.lambda$descendantOf$2((AlbumGroupView.Group) obj);
            case 3:
                return AlbumGroupView.Group.lambda$descendantOf$4((String) obj);
            case 4:
                return AlbumGroupView.Group.lambda$descendantOf$5((AlbumGroupView.Group) obj);
            default:
                return AlbumGroupView.Group.lambda$descendantOf$7((String) obj);
        }
    }
}
