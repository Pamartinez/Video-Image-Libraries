package S3;

import android.content.Context;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ RequestSharedAlbumCmd d;
    public final /* synthetic */ RequestCmdType e;
    public final /* synthetic */ EventContext f;
    public final /* synthetic */ Context g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object[] f2422h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ long f2423i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ long f2424j;

    public /* synthetic */ e(RequestSharedAlbumCmd requestSharedAlbumCmd, RequestCmdType requestCmdType, EventContext eventContext, Context context, Object[] objArr, long j2, long j3) {
        this.d = requestSharedAlbumCmd;
        this.e = requestCmdType;
        this.f = eventContext;
        this.g = context;
        this.f2422h = objArr;
        this.f2423i = j2;
        this.f2424j = j3;
    }

    public final void run() {
        this.d.lambda$onExecute$0(this.e, this.f, this.g, this.f2422h, this.f2423i, this.f2424j);
    }
}
