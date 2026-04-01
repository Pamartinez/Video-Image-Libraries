package M4;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ QueryParams e;

    public /* synthetic */ l(int i2, QueryParams queryParams) {
        this.d = i2;
        this.e = queryParams;
    }

    public final Object get() {
        int i2 = this.d;
        QueryParams queryParams = this.e;
        switch (i2) {
            case 0:
                return new MpHelper(queryParams).getAlbumCount();
            default:
                return new MpHelper(queryParams).getAlbumCount();
        }
    }
}
