package C0;

import androidx.collection.LruCache;
import x0.C0332j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h {
    public static final h b = new h();

    /* renamed from: a  reason: collision with root package name */
    public final LruCache f98a = new LruCache(20);

    public final C0332j a(String str) {
        if (str == null) {
            return null;
        }
        return (C0332j) this.f98a.get(str);
    }
}
