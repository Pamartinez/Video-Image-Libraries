package ha;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ long f;

    public /* synthetic */ a(int i2, long j2, int i7) {
        this.d = i7;
        this.e = i2;
        this.f = j2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((QueryParams) obj).addGroupType(GroupType.SIMILAR).setGroupMediaFilter(this.e, this.f);
                return;
            case 1:
                ((QueryParams) obj).setGroupTypes(GroupType.BURST).setGroupMediaFilter(this.e, this.f);
                return;
            default:
                ((QueryParams) obj).setGroupTypes(GroupType.SINGLE_TAKEN).setGroupMediaFilter(this.e, this.f);
                return;
        }
    }
}
