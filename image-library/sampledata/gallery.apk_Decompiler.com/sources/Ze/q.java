package Ze;

import D0.e;
import me.i;
import ne.z;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class q {

    /* renamed from: a  reason: collision with root package name */
    public static final C1236c f3953a;
    public static final C1236c[] b;

    /* renamed from: c  reason: collision with root package name */
    public static final e f3954c;
    public static final r d;

    static {
        C1236c cVar = new C1236c("org.jspecify.nullness");
        C1236c cVar2 = new C1236c("org.jspecify.annotations");
        f3953a = cVar2;
        C1236c cVar3 = new C1236c("io.reactivex.rxjava3.annotations");
        C1236c cVar4 = new C1236c("org.checkerframework.checker.nullness.compatqual");
        String b5 = cVar3.b();
        b = new C1236c[]{new C1236c(b5.concat(".Nullable")), new C1236c(b5.concat(".NonNull"))};
        C1236c cVar5 = new C1236c("org.jetbrains.annotations");
        r rVar = r.d;
        i iVar = new i(cVar5, rVar);
        i iVar2 = new i(new C1236c("androidx.annotation"), rVar);
        i iVar3 = new i(new C1236c("android.support.annotation"), rVar);
        i iVar4 = new i(new C1236c("android.annotation"), rVar);
        i iVar5 = new i(new C1236c("com.android.annotations"), rVar);
        i iVar6 = new i(new C1236c("org.eclipse.jdt.annotation"), rVar);
        i iVar7 = new i(new C1236c("org.checkerframework.checker.nullness.qual"), rVar);
        i iVar8 = new i(cVar4, rVar);
        i iVar9 = new i(new C1236c("javax.annotation"), rVar);
        i iVar10 = new i(new C1236c("edu.umd.cs.findbugs.annotations"), rVar);
        i iVar11 = new i(new C1236c("io.reactivex.annotations"), rVar);
        C1236c cVar6 = new C1236c("androidx.annotation.RecentlyNullable");
        C c5 = C.WARN;
        i iVar12 = iVar;
        i iVar13 = new i(cVar6, new r(c5, 4));
        i iVar14 = new i(new C1236c("androidx.annotation.RecentlyNonNull"), new r(c5, 4));
        i iVar15 = new i(new C1236c("lombok"), rVar);
        me.e eVar = new me.e(2, 1, 0);
        C c6 = C.STRICT;
        i iVar16 = iVar2;
        f3954c = new e(z.b0(iVar12, iVar16, iVar3, iVar4, iVar5, iVar6, iVar7, iVar8, iVar9, iVar10, iVar11, iVar13, iVar14, iVar15, new i(cVar, new r(c5, eVar, c6)), new i(cVar2, new r(c5, new me.e(2, 1, 0), c6)), new i(cVar3, new r(c5, new me.e(1, 8, 0), c6))));
        d = new r(c5, 4);
    }
}
