package com.samsung.android.gallery.database.dal.local;

import com.samsung.android.gallery.database.dal.local.AlbumGroupView;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Comparable {
    public final /* synthetic */ int d;
    public final /* synthetic */ HashSet e;

    public /* synthetic */ h(HashSet hashSet, int i2) {
        this.d = i2;
        this.e = hashSet;
    }

    public final int compareTo(Object obj) {
        int i2 = this.d;
        HashSet hashSet = this.e;
        Long l = (Long) obj;
        switch (i2) {
            case 0:
                return AlbumGroupView.SqliteArg.lambda$getLongComparator$0(hashSet, l);
            default:
                return AlbumGroupView.SqliteArg.lambda$getLongComparator$1(hashSet, l);
        }
    }
}
