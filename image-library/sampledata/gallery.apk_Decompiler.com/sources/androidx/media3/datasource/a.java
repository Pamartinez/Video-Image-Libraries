package androidx.media3.datasource;

import E2.l;
import androidx.media3.datasource.DefaultHttpDataSource;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements l {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final boolean apply(Object obj) {
        switch (this.d) {
            case 0:
                return DefaultHttpDataSource.NullFilteringHeadersMap.lambda$entrySet$1((Map.Entry) obj);
            default:
                return DefaultHttpDataSource.NullFilteringHeadersMap.lambda$keySet$0((String) obj);
        }
    }
}
