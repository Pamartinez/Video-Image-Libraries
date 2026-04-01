package a6;

import android.graphics.Bitmap;
import androidx.core.util.Consumer;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OnDemandLoader f2473a;
    public final /* synthetic */ ArrayList b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2474c;
    public final /* synthetic */ AtomicInteger d;
    public final /* synthetic */ List e;
    public final /* synthetic */ String f;
    public final /* synthetic */ long g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Consumer f2475h;

    public /* synthetic */ f(OnDemandLoader onDemandLoader, ArrayList arrayList, MediaItem mediaItem, AtomicInteger atomicInteger, List list, String str, long j2, Consumer consumer) {
        this.f2473a = onDemandLoader;
        this.b = arrayList;
        this.f2474c = mediaItem;
        this.d = atomicInteger;
        this.e = list;
        this.f = str;
        this.g = j2;
        this.f2475h = consumer;
    }

    public final void accept(Object obj) {
        this.f2473a.lambda$loadThumbnails$3(this.b, this.f2474c, this.d, this.e, this.f, this.g, this.f2475h, (Bitmap) obj);
    }
}
