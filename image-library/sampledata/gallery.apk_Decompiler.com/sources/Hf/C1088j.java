package hf;

import Ae.b;
import kotlin.jvm.internal.j;
import me.x;

/* renamed from: hf.j  reason: case insensitive filesystem */
public final class C1088j implements b {
    public final /* synthetic */ int d;
    public final String e;
    public final String f;

    public /* synthetic */ C1088j(String str, String str2, int i2) {
        this.d = i2;
        this.e = str;
        this.f = str2;
    }

    public final Object invoke(Object obj) {
        C1092n nVar = (C1092n) obj;
        switch (this.d) {
            case 0:
                j.e(nVar, "$this$function");
                C1082d dVar = C1089k.b;
                String str = this.e;
                nVar.a(str, dVar);
                C1082d dVar2 = C1089k.f4589a;
                nVar.a(this.f, dVar, dVar, dVar2, dVar2);
                nVar.b(str, dVar2);
                return x.f4917a;
            case 1:
                j.e(nVar, "$this$function");
                C1082d dVar3 = C1089k.b;
                String str2 = this.e;
                nVar.a(str2, dVar3);
                nVar.a(this.f, dVar3, dVar3, dVar3);
                nVar.b(str2, dVar3);
                return x.f4917a;
            case 2:
                j.e(nVar, "$this$function");
                C1082d dVar4 = C1089k.b;
                String str3 = this.e;
                nVar.a(str3, dVar4);
                C1082d dVar5 = C1089k.f4590c;
                C1082d dVar6 = C1089k.f4589a;
                nVar.a(this.f, dVar4, dVar4, dVar5, dVar6);
                nVar.b(str3, dVar6);
                return x.f4917a;
            case 3:
                j.e(nVar, "$this$function");
                C1082d dVar7 = C1089k.b;
                String str4 = this.e;
                nVar.a(str4, dVar7);
                C1082d dVar8 = C1089k.f4590c;
                nVar.a(str4, dVar8);
                C1082d dVar9 = C1089k.f4589a;
                nVar.a(this.f, dVar7, dVar8, dVar8, dVar9);
                nVar.b(str4, dVar9);
                return x.f4917a;
            case 4:
                j.e(nVar, "$this$function");
                C1082d dVar10 = C1089k.f4590c;
                nVar.a(this.e, dVar10);
                nVar.b(this.f, C1089k.b, dVar10);
                return x.f4917a;
            default:
                j.e(nVar, "$this$function");
                nVar.a(this.e, C1089k.f4589a);
                nVar.b(this.f, C1089k.b, C1089k.f4590c);
                return x.f4917a;
        }
    }
}
