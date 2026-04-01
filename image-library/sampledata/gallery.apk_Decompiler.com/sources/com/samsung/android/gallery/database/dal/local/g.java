package com.samsung.android.gallery.database.dal.local;

import com.samsung.android.gallery.database.dal.local.AlbumGroupView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Comparable {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;

    public /* synthetic */ g(long j2, int i2) {
        this.d = i2;
        this.e = j2;
    }

    public final int compareTo(Object obj) {
        switch (this.d) {
            case 0:
                return AlbumGroupView.SqliteArg.lambda$getLongComparator$6(this.e, (Long) obj);
            case 1:
                return AlbumGroupView.SqliteArg.lambda$getLongComparator$7(this.e, (Long) obj);
            case 2:
                return AlbumGroupView.SqliteArg.lambda$getLongComparator$2(this.e, (Long) obj);
            case 3:
                return AlbumGroupView.SqliteArg.lambda$getLongComparator$3(this.e, (Long) obj);
            case 4:
                return AlbumGroupView.SqliteArg.lambda$getLongComparator$4(this.e, (Long) obj);
            default:
                return AlbumGroupView.SqliteArg.lambda$getLongComparator$5(this.e, (Long) obj);
        }
    }
}
