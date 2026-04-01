package lg;

import a.C0068a;
import ig.f;
import ig.l;
import java.util.List;
import kg.B;
import kg.e0;
import kotlin.jvm.internal.j;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class z implements f {
    public static final z b = new z();

    /* renamed from: c  reason: collision with root package name */
    public static final String f4911c = "kotlinx.serialization.json.JsonObject";

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ B f4912a;

    public z() {
        e0 e0Var = e0.f4693a;
        p pVar = p.f4907a;
        e0 e0Var2 = e0.f4693a;
        p pVar2 = p.f4907a;
        f descriptor = e0Var2.getDescriptor();
        f descriptor2 = pVar2.getDescriptor();
        j.e(descriptor, "keyDesc");
        j.e(descriptor2, "valueDesc");
        this.f4912a = new B("kotlin.collections.LinkedHashMap", descriptor, descriptor2);
    }

    public final C0068a b() {
        this.f4912a.getClass();
        return l.f;
    }

    public final boolean c() {
        this.f4912a.getClass();
        return false;
    }

    public final int d(String str) {
        j.e(str, "name");
        return this.f4912a.d(str);
    }

    public final int e() {
        this.f4912a.getClass();
        return 2;
    }

    public final String f(int i2) {
        this.f4912a.getClass();
        return String.valueOf(i2);
    }

    public final List g(int i2) {
        this.f4912a.g(i2);
        return C1202t.d;
    }

    public final List getAnnotations() {
        this.f4912a.getClass();
        return C1202t.d;
    }

    public final f h(int i2) {
        return this.f4912a.h(i2);
    }

    public final String i() {
        return f4911c;
    }

    public final boolean isInline() {
        this.f4912a.getClass();
        return false;
    }

    public final boolean j(int i2) {
        this.f4912a.j(i2);
        return false;
    }
}
