package M9;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.publisher.MapDataPublisher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ QueryParams f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ long f2844h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ long f2845i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ String f2846j;

    public /* synthetic */ i(Cursor[] cursorArr, QueryParams queryParams, String str, long j2, long j3, String str2, int i2) {
        this.d = i2;
        this.e = cursorArr;
        this.f = queryParams;
        this.g = str;
        this.f2844h = j2;
        this.f2845i = j3;
        this.f2846j = str2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                MapDataPublisher.lambda$queryFilteredMapData$2(this.e, this.f, this.g, this.f2844h, this.f2845i, this.f2846j);
                return;
            default:
                MapDataPublisher.lambda$queryFilteredMapData$3(this.e, this.f, this.g, this.f2844h, this.f2845i, this.f2846j);
                return;
        }
    }
}
