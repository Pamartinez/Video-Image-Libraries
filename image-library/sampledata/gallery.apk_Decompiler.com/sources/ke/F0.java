package Ke;

import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class F0 {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f3490a;
    public final int b;

    public F0(ClassLoader classLoader) {
        this.f3490a = new WeakReference(classLoader);
        this.b = System.identityHashCode(classLoader);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof F0) || this.f3490a.get() != ((F0) obj).f3490a.get()) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.b;
    }

    public final String toString() {
        String obj;
        ClassLoader classLoader = (ClassLoader) this.f3490a.get();
        if (classLoader == null || (obj = classLoader.toString()) == null) {
            return "<null>";
        }
        return obj;
    }
}
