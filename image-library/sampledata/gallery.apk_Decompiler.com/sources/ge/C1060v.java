package ge;

import D1.f;
import ee.C0969b;
import ee.r;
import java.util.Arrays;

/* renamed from: ge.v  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1060v {

    /* renamed from: a  reason: collision with root package name */
    public String f4562a;
    public C0969b b;

    /* renamed from: c  reason: collision with root package name */
    public r f4563c;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1060v)) {
            return false;
        }
        C1060v vVar = (C1060v) obj;
        if (!this.f4562a.equals(vVar.f4562a) || !this.b.equals(vVar.b) || !f.p((Object) null, (Object) null) || !f.p(this.f4563c, vVar.f4563c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4562a, this.b, null, this.f4563c});
    }
}
