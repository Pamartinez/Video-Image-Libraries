package ta;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import java.util.function.Consumer;

/* renamed from: ta.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0707a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int[] e;
    public final /* synthetic */ int f;

    public /* synthetic */ C0707a(int[] iArr, int i2, int i7) {
        this.d = i7;
        this.e = iArr;
        this.f = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((QueryParams) obj).setGroupTypes(new GroupType[0]).addAlbumIds(this.e).setLimit(this.f, 25000);
                return;
            default:
                ((QueryParams) obj).setGroupTypes(new GroupType[0]).addAlbumIds(this.e).onlyTrashed().setLimit(this.f, 25000);
                return;
        }
    }
}
