package kg;

import He.C0748d;
import Uf.a;
import java.util.Locale;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import me.i;
import me.o;
import me.p;
import me.q;
import me.r;
import me.s;
import me.t;
import me.x;
import ne.z;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Y {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f4685a;

    static {
        w wVar = v.f4727a;
        i iVar = new i(wVar.b(String.class), e0.f4693a);
        i iVar2 = new i(wVar.b(Character.TYPE), C1132m.f4706a);
        i iVar3 = new i(wVar.b(char[].class), C1131l.f4704c);
        i iVar4 = new i(wVar.b(Double.TYPE), C1136q.f4714a);
        i iVar5 = new i(wVar.b(double[].class), C1135p.f4712c);
        i iVar6 = new i(wVar.b(Float.TYPE), C1144z.f4725a);
        i iVar7 = new i(wVar.b(float[].class), C1143y.f4724c);
        i iVar8 = new i(wVar.b(Long.TYPE), L.f4673a);
        i iVar9 = new i(wVar.b(long[].class), K.f4672c);
        i iVar10 = new i(wVar.b(s.class), o0.f4711a);
        i iVar11 = new i(wVar.b(t.class), n0.f4709c);
        i iVar12 = new i(wVar.b(Integer.TYPE), H.f4669a);
        i iVar13 = new i(wVar.b(int[].class), G.f4668c);
        i iVar14 = iVar;
        i iVar15 = new i(wVar.b(q.class), l0.f4705a);
        i iVar16 = new i(wVar.b(r.class), k0.f4703c);
        i iVar17 = new i(wVar.b(Short.TYPE), d0.f4691a);
        i iVar18 = new i(wVar.b(short[].class), c0.f4689c);
        i iVar19 = new i(wVar.b(me.v.class), r0.f4717a);
        i iVar20 = new i(wVar.b(me.w.class), q0.f4715c);
        i iVar21 = new i(wVar.b(Byte.TYPE), C1128i.f4699a);
        i iVar22 = new i(wVar.b(byte[].class), C1127h.f4697c);
        i iVar23 = new i(wVar.b(o.class), i0.f4700a);
        i iVar24 = new i(wVar.b(p.class), h0.f4698c);
        i iVar25 = new i(wVar.b(Boolean.TYPE), C1125f.f4694a);
        i iVar26 = new i(wVar.b(boolean[].class), C1124e.f4692c);
        i iVar27 = new i(wVar.b(x.class), s0.b);
        i iVar28 = new i(wVar.b(Void.class), N.f4675a);
        C0748d b = wVar.b(a.class);
        int i2 = a.g;
        f4685a = z.b0(iVar14, iVar2, iVar3, iVar4, iVar5, iVar6, iVar7, iVar8, iVar9, iVar10, iVar11, iVar12, iVar13, iVar15, iVar16, iVar17, iVar18, iVar19, iVar20, iVar21, iVar22, iVar23, iVar24, iVar25, iVar26, iVar27, iVar28, new i(b, r.f4716a));
    }

    public static final String a(String str) {
        String str2;
        if (str.length() <= 0) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder();
        char charAt = str.charAt(0);
        if (Character.isLowerCase(charAt)) {
            String valueOf = String.valueOf(charAt);
            j.c(valueOf, "null cannot be cast to non-null type java.lang.String");
            Locale locale = Locale.ROOT;
            str2 = valueOf.toUpperCase(locale);
            j.d(str2, "toUpperCase(...)");
            if (str2.length() <= 1) {
                str2 = String.valueOf(Character.toTitleCase(charAt));
            } else if (charAt != 329) {
                char charAt2 = str2.charAt(0);
                String substring = str2.substring(1);
                j.d(substring, "substring(...)");
                String lowerCase = substring.toLowerCase(locale);
                j.d(lowerCase, "toLowerCase(...)");
                str2 = charAt2 + lowerCase;
            }
        } else {
            str2 = String.valueOf(charAt);
        }
        sb2.append(str2);
        String substring2 = str.substring(1);
        j.d(substring2, "substring(...)");
        sb2.append(substring2);
        return sb2.toString();
    }
}
