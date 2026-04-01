package Sd;

import D0.e;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.samsung.android.gallery.module.cloud.a;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3718a;
    public final /* synthetic */ e b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Consumer f3719c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(e eVar, Handler handler, Consumer consumer, int i2) {
        super(handler);
        this.f3718a = i2;
        this.b = eVar;
        this.f3719c = consumer;
    }

    public final void onChange(boolean z, Uri uri) {
        switch (this.f3718a) {
            case 0:
                super.onChange(z, uri);
                Log.d((String) this.b.f, "createSyncSettingContentObserver: " + uri);
                String encodedQuery = uri.getEncodedQuery();
                if (encodedQuery != null && !encodedQuery.isEmpty()) {
                    int parseInt = Integer.parseInt(encodedQuery);
                    z[] values = z.values();
                    if (parseInt < values.length) {
                        ((a) this.f3719c).accept(values[parseInt]);
                        return;
                    }
                    return;
                }
                return;
            default:
                super.onChange(z, uri);
                String encodedQuery2 = uri.getEncodedQuery();
                Log.d((String) this.b.f, "createSyncStatusContentObserver: " + uri);
                if (encodedQuery2 != null && !encodedQuery2.isEmpty()) {
                    int parseInt2 = Integer.parseInt(encodedQuery2);
                    B[] values2 = B.values();
                    if (parseInt2 < values2.length) {
                        ((a) this.f3719c).accept(values2[parseInt2]);
                        return;
                    }
                    return;
                }
                return;
        }
    }
}
