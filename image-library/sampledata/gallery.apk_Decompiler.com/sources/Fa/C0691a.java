package fa;

import E7.c;
import com.samsung.android.gallery.app.ui.container.menu.TabMenuHelper;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import java.util.Optional;
import java.util.function.Consumer;

/* renamed from: fa.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0691a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ C0691a(int i2, boolean z, int i7) {
        this.d = i7;
        this.e = i2;
        this.f = z;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((QueryParams) obj).addAlbumId(this.e).setWithEmptyAlbums(this.f).setGroupTypes(GroupType.SINGLE_TAKEN, GroupType.BURST).setShowHidden(false).setAlbumCount(0).addDataStamp();
                return;
            default:
                Optional.ofNullable(TabMenuHelper.getLocationKeyById(this.e)).ifPresent(new c((DrawerTabViewAdapter) obj, this.f, 17));
                return;
        }
    }
}
