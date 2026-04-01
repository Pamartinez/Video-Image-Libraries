package vf;

import Hf.C0774x;
import kotlin.jvm.internal.j;

/* renamed from: vf.r  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1338r extends C1340t {

    /* renamed from: a  reason: collision with root package name */
    public final C0774x f5162a;

    public C1338r(C0774x xVar) {
        this.f5162a = xVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof C1338r) && j.a(this.f5162a, ((C1338r) obj).f5162a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f5162a.hashCode();
    }

    public final String toString() {
        return "LocalClass(type=" + this.f5162a + ')';
    }
}
