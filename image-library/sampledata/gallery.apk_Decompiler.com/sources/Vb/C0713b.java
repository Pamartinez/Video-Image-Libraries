package vb;

import android.content.ClipData;
import android.net.Uri;
import java.util.function.Predicate;

/* renamed from: vb.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0713b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3298a;
    public final /* synthetic */ Uri b;

    public /* synthetic */ C0713b(Uri uri, int i2) {
        this.f3298a = i2;
        this.b = uri;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3298a;
        Uri uri = this.b;
        ClipData.Item item = (ClipData.Item) obj;
        switch (i2) {
            case 0:
                return uri.equals(item.getUri());
            default:
                return uri.equals(item.getUri());
        }
    }
}
