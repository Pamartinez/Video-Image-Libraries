package E3;

import android.net.Uri;
import com.samsung.android.gallery.app.activity.external.launcher.UriItemViewLauncher;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ UriItemViewLauncher d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ AtomicReference f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Uri f2314h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int[] f2315i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ MediaItem[] f2316j;
    public final /* synthetic */ CountDownLatch k;

    public /* synthetic */ i(UriItemViewLauncher uriItemViewLauncher, ArrayList arrayList, AtomicReference atomicReference, boolean z, Uri uri, int[] iArr, MediaItem[] mediaItemArr, CountDownLatch countDownLatch) {
        this.d = uriItemViewLauncher;
        this.e = arrayList;
        this.f = atomicReference;
        this.g = z;
        this.f2314h = uri;
        this.f2315i = iArr;
        this.f2316j = mediaItemArr;
        this.k = countDownLatch;
    }

    public final void run() {
        this.d.lambda$execute$1(this.e, this.f, this.g, this.f2314h, this.f2315i, this.f2316j, this.k);
    }
}
