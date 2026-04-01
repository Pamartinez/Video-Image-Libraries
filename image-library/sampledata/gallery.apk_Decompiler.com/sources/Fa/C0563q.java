package Fa;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.download.CloudDownloadTask;
import com.samsung.android.gallery.support.cache.CacheManager;
import java.util.HashMap;
import java.util.function.ToLongFunction;

/* renamed from: Fa.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0563q implements ToLongFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2814a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0563q(int i2, Object obj) {
        this.f2814a = i2;
        this.b = obj;
    }

    public final long applyAsLong(Object obj) {
        int i2 = this.f2814a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((Long) ((HashMap) obj2).getOrDefault((String) obj, 0L)).longValue();
            case 1:
                return ((CacheManager) obj2).lambda$max$0((Integer) obj);
            default:
                return ((CloudDownloadTask) obj2).getFileSize((MediaItem) obj);
        }
    }
}
