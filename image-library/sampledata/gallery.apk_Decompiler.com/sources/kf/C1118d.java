package kf;

import Bf.a;
import G0.c;

/* renamed from: kf.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1118d extends a {
    public final /* synthetic */ int f;
    public final /* synthetic */ c g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1118d(c cVar, int i2) {
        super(4);
        this.f = i2;
        this.g = cVar;
    }

    public final void B0(String[] strArr) {
        switch (this.f) {
            case 0:
                if (strArr != null) {
                    ((C1119e) this.g.e).g = strArr;
                    return;
                }
                throw new IllegalArgumentException("Argument for @NotNull parameter 'data' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor$1.visitEnd must not be null");
            default:
                if (strArr != null) {
                    ((C1119e) this.g.e).f4659h = strArr;
                    return;
                }
                throw new IllegalArgumentException("Argument for @NotNull parameter 'data' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor$2.visitEnd must not be null");
        }
    }
}
