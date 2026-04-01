package ta;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import java.util.function.Consumer;

/* renamed from: ta.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0708b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int[] e;

    public /* synthetic */ C0708b(int[] iArr, int i2) {
        this.d = i2;
        this.e = iArr;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        int[] iArr = this.e;
        switch (i2) {
            case 0:
                ((QueryParams) obj).addGroupType(GroupType.SINGLE_TAKEN).addAlbumIds(iArr).onlyTrashed();
                return;
            case 1:
                ((QueryParams) obj).addGroupType(GroupType.SINGLE_TAKEN).addAlbumIds(iArr);
                return;
            default:
                ((LinearLayoutManager) obj).scrollToPositionWithOffset(iArr[0], iArr[1]);
                return;
        }
    }
}
