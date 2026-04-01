package M9;

import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ QueryParams e;

    public /* synthetic */ a(int i2, QueryParams queryParams) {
        this.d = i2;
        this.e = queryParams;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.excludeAlbumId(((Integer) obj).intValue());
                return;
            case 1:
                this.e.setMediaTypeFilter((String) obj);
                return;
            default:
                this.e.lambda$addUris$0((Uri) obj);
                return;
        }
    }
}
