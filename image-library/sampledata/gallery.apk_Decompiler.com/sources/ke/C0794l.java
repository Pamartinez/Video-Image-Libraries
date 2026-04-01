package Ke;

import We.C0892d;
import Ze.w;
import a.C0068a;
import java.lang.reflect.Field;
import kotlin.jvm.internal.j;

/* renamed from: Ke.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0794l extends C0068a {
    public final Field d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0794l(Field field) {
        super(4);
        j.e(field, "field");
        this.d = field;
    }

    public final String h() {
        StringBuilder sb2 = new StringBuilder();
        Field field = this.d;
        String name = field.getName();
        j.d(name, "getName(...)");
        sb2.append(w.a(name));
        sb2.append("()");
        Class<?> type = field.getType();
        j.d(type, "getType(...)");
        sb2.append(C0892d.b(type));
        return sb2.toString();
    }
}
