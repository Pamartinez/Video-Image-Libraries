package Ze;

import Ne.p;
import java.util.LinkedHashSet;
import java.util.Set;
import me.i;
import ne.C1182C;
import ne.C1192j;
import ne.z;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class y {

    /* renamed from: a  reason: collision with root package name */
    public static final C1236c f3970a;
    public static final C1236c b;

    /* renamed from: c  reason: collision with root package name */
    public static final C1236c f3971c;
    public static final C1236c d = new C1236c("javax.annotation.meta.TypeQualifier");
    public static final C1236c e = new C1236c("javax.annotation.meta.TypeQualifierNickname");
    public static final C1236c f = new C1236c("javax.annotation.meta.TypeQualifierDefault");
    public static final C1236c g;

    /* renamed from: h  reason: collision with root package name */
    public static final C1236c f3972h = new C1236c("javax.annotation.ParametersAreNonnullByDefault");

    /* renamed from: i  reason: collision with root package name */
    public static final C1236c f3973i = new C1236c("javax.annotation.ParametersAreNullableByDefault");

    /* renamed from: j  reason: collision with root package name */
    public static final Set f3974j;
    public static final Set k;
    public static final Set l;
    public static final Set m;
    public static final Set n = C1192j.z0(new C1236c[]{x.k, x.l});

    /* renamed from: o  reason: collision with root package name */
    public static final Set f3975o = C1192j.z0(new C1236c[]{x.f3967j, x.m});

    /* renamed from: p  reason: collision with root package name */
    public static final C1236c f3976p = new C1236c("kotlin.annotations.jvm.UnderMigration");

    static {
        C1236c cVar = new C1236c("org.jspecify.nullness.Nullable");
        C1236c cVar2 = new C1236c("org.jspecify.nullness.NullMarked");
        f3970a = cVar2;
        C1236c cVar3 = new C1236c("org.jspecify.nullness.NullnessUnspecified");
        C1236c cVar4 = new C1236c("org.jspecify.annotations.NonNull");
        C1236c cVar5 = new C1236c("org.jspecify.annotations.Nullable");
        C1236c cVar6 = new C1236c("org.jspecify.annotations.NullMarked");
        b = cVar6;
        C1236c cVar7 = new C1236c("org.jspecify.annotations.NullnessUnspecified");
        C1236c cVar8 = new C1236c("org.jspecify.annotations.NullUnmarked");
        f3971c = cVar8;
        C1236c cVar9 = new C1236c("javax.annotation.Nonnull");
        g = cVar9;
        C1236c cVar10 = new C1236c("javax.annotation.Nullable");
        C1236c cVar11 = new C1236c("javax.annotation.CheckForNull");
        f3974j = C1192j.z0(new C1236c[]{cVar9, cVar11});
        C1236c cVar12 = cVar6;
        C1236c cVar13 = cVar7;
        C1236c cVar14 = cVar8;
        C1236c cVar15 = cVar9;
        C1236c cVar16 = cVar3;
        C1236c cVar17 = cVar10;
        C1236c cVar18 = cVar11;
        C1236c cVar19 = cVar17;
        C1236c cVar20 = cVar12;
        C1236c cVar21 = cVar13;
        C1236c cVar22 = cVar14;
        C1236c cVar23 = cVar18;
        C1236c cVar24 = cVar15;
        C1236c cVar25 = cVar;
        C1236c cVar26 = cVar20;
        C1236c cVar27 = cVar19;
        Set z02 = C1192j.z0(new C1236c[]{x.f3965h, cVar4, new C1236c("android.annotation.NonNull"), new C1236c("androidx.annotation.NonNull"), new C1236c("androidx.annotation.RecentlyNonNull"), new C1236c("androidx.annotation.NonNull"), new C1236c("com.android.annotations.NonNull"), new C1236c("org.checkerframework.checker.nullness.compatqual.NonNullDecl"), new C1236c("org.checkerframework.checker.nullness.qual.NonNull"), new C1236c("edu.umd.cs.findbugs.annotations.NonNull"), new C1236c("io.reactivex.annotations.NonNull"), new C1236c("io.reactivex.rxjava3.annotations.NonNull"), new C1236c("org.eclipse.jdt.annotation.NonNull"), new C1236c("lombok.NonNull")});
        k = z02;
        C1236c cVar28 = cVar26;
        C1236c cVar29 = x.f3966i;
        C1236c cVar30 = new C1236c("android.annotation.Nullable");
        C1236c cVar31 = new C1236c("androidx.annotation.Nullable");
        C1236c cVar32 = new C1236c("androidx.annotation.RecentlyNullable");
        C1236c cVar33 = new C1236c("androidx.annotation.Nullable");
        C1236c cVar34 = new C1236c("com.android.annotations.Nullable");
        C1236c cVar35 = new C1236c("org.checkerframework.checker.nullness.compatqual.NullableDecl");
        C1236c cVar36 = new C1236c("org.checkerframework.checker.nullness.qual.Nullable");
        C1236c cVar37 = new C1236c("edu.umd.cs.findbugs.annotations.Nullable");
        C1236c cVar38 = new C1236c("edu.umd.cs.findbugs.annotations.PossiblyNull");
        C1236c cVar39 = new C1236c("edu.umd.cs.findbugs.annotations.CheckForNull");
        C1236c cVar40 = new C1236c("io.reactivex.annotations.Nullable");
        C1236c cVar41 = cVar28;
        C1236c cVar42 = cVar16;
        C1236c cVar43 = cVar40;
        C1236c cVar44 = cVar23;
        C1236c cVar45 = cVar25;
        Set z03 = C1192j.z0(new C1236c[]{cVar29, cVar45, cVar5, cVar27, cVar44, cVar30, cVar31, cVar32, cVar33, cVar34, cVar35, cVar36, cVar37, cVar38, cVar39, cVar43, new C1236c("io.reactivex.rxjava3.annotations.Nullable"), new C1236c("org.eclipse.jdt.annotation.Nullable")});
        l = z03;
        m = C1192j.z0(new C1236c[]{cVar42, cVar21});
        C1182C.c0(C1182C.c0(C1182C.c0(C1182C.c0(C1182C.b0(C1182C.b0(new LinkedHashSet(), z02), z03), cVar24), cVar2), cVar41), cVar22);
        z.b0(new i(x.f3964c, p.t), new i(x.d, p.w), new i(x.e, p.m), new i(x.f, p.f3573x));
    }
}
