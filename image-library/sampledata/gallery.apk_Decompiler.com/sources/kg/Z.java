package kg;

import a.C0068a;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import ig.f;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Z implements f, C1129j {

    /* renamed from: a  reason: collision with root package name */
    public final f f4686a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final Set f4687c;

    public Z(f fVar) {
        j.e(fVar, ShareApi.ORIGINAL_SIZE_IMAGE);
        this.f4686a = fVar;
        this.b = fVar.i() + '?';
        this.f4687c = Q.b(fVar);
    }

    public final Set a() {
        return this.f4687c;
    }

    public final C0068a b() {
        return this.f4686a.b();
    }

    public final boolean c() {
        return true;
    }

    public final int d(String str) {
        j.e(str, "name");
        return this.f4686a.d(str);
    }

    public final int e() {
        return this.f4686a.e();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Z)) {
            return false;
        }
        if (!j.a(this.f4686a, ((Z) obj).f4686a)) {
            return false;
        }
        return true;
    }

    public final String f(int i2) {
        return this.f4686a.f(i2);
    }

    public final List g(int i2) {
        return this.f4686a.g(i2);
    }

    public final List getAnnotations() {
        return this.f4686a.getAnnotations();
    }

    public final f h(int i2) {
        return this.f4686a.h(i2);
    }

    public final int hashCode() {
        return this.f4686a.hashCode() * 31;
    }

    public final String i() {
        return this.b;
    }

    public final boolean isInline() {
        return this.f4686a.isInline();
    }

    public final boolean j(int i2) {
        return this.f4686a.j(i2);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f4686a);
        sb2.append('?');
        return sb2.toString();
    }
}
