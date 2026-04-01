package Xf;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l {
    public static final k b = new Object();

    /* renamed from: a  reason: collision with root package name */
    public final Object f3911a;

    public /* synthetic */ l(Object obj) {
        this.f3911a = obj;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        if (!j.a(this.f3911a, ((l) obj).f3911a)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Object obj = this.f3911a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        Object obj = this.f3911a;
        if (obj instanceof j) {
            return ((j) obj).toString();
        }
        return "Value(" + obj + ')';
    }
}
