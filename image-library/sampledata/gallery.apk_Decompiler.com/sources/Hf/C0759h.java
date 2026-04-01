package Hf;

import Re.h;
import kotlin.jvm.internal.j;

/* renamed from: Hf.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0759h {

    /* renamed from: a  reason: collision with root package name */
    public final h f3445a;

    public C0759h(h hVar) {
        j.e(hVar, "annotations");
        this.f3445a = hVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0759h)) {
            return false;
        }
        return j.a(((C0759h) obj).f3445a, this.f3445a);
    }

    public final int hashCode() {
        return this.f3445a.hashCode();
    }
}
