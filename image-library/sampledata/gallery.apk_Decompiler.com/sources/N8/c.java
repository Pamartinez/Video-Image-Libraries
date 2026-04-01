package N8;

import android.content.ContentUris;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Uri e;

    public /* synthetic */ c(Uri uri, int i2) {
        this.d = i2;
        this.e = uri;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Uri uri = this.e;
        switch (i2) {
            case 0:
                ((Consumer) obj).accept(uri);
                return;
            default:
                ((QueryParams) obj).setMediaId(ContentUris.parseId(uri));
                return;
        }
    }
}
