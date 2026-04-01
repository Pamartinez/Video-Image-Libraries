package com.samsung.android.gallery.database.dal.local;

import android.database.MatrixCursor;
import com.samsung.android.gallery.database.dal.local.AlbumGroupView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                AlbumGroupView.lambda$query$4((HashMap) obj2, (AlbumGroupView.Group) obj);
                return;
            case 1:
                ((MatrixCursor) obj2).addRow(((AlbumGroupView.Group) obj).buildContentValues());
                return;
            default:
                ((ArrayList) obj2).add(((AlbumGroupView.Group) obj).listOf());
                return;
        }
    }
}
