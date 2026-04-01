package M4;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.database.dbtype.MediaType;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements IntSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2396a;
    public final /* synthetic */ QueryParams b;

    public /* synthetic */ k(int i2, QueryParams queryParams) {
        this.f2396a = i2;
        this.b = queryParams;
    }

    public final int getAsInt() {
        int i2 = this.f2396a;
        QueryParams queryParams = this.b;
        switch (i2) {
            case 0:
                return new MpHelper(queryParams).getAlbumCount(MediaType.Video);
            default:
                return new MpHelper(queryParams).getAlbumCount(MediaType.Video);
        }
    }
}
