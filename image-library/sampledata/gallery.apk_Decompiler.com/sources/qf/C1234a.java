package qf;

import Tf.v;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import kotlin.jvm.internal.j;

/* renamed from: qf.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1234a {

    /* renamed from: a  reason: collision with root package name */
    public final C1236c f5032a;
    public final C1240g b;

    static {
        C1236c.j(C1242i.f);
    }

    public C1234a(C1236c cVar, C1240g gVar) {
        j.e(cVar, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        this.f5032a = cVar;
        this.b = gVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1234a)) {
            return false;
        }
        C1234a aVar = (C1234a) obj;
        if (!j.a(this.f5032a, aVar.f5032a) || !this.b.equals(aVar.b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.b.hashCode() + ((this.f5032a.hashCode() + 527) * 961);
    }

    public final String toString() {
        String str = v.r0(this.f5032a.b(), '.', '/') + "/" + this.b;
        j.d(str, "toString(...)");
        return str;
    }
}
