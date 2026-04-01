package kf;

import B1.b;
import Bf.a;
import G0.e;
import jf.o;

/* renamed from: kf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1117c extends a {
    public final /* synthetic */ int f;
    public final /* synthetic */ o g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1117c(o oVar, int i2) {
        super(4);
        this.f = i2;
        this.g = oVar;
    }

    public final void B0(String[] strArr) {
        switch (this.f) {
            case 0:
                if (strArr != null) {
                    ((C1119e) ((e) this.g).d).g = strArr;
                    return;
                }
                throw new IllegalArgumentException("Argument for @NotNull parameter 'result' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor$1.visitEnd must not be null");
            case 1:
                if (strArr != null) {
                    ((C1119e) ((e) this.g).d).f4659h = strArr;
                    return;
                }
                throw new IllegalArgumentException("Argument for @NotNull parameter 'result' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor$2.visitEnd must not be null");
            default:
                if (strArr != null) {
                    ((C1119e) ((b) this.g).e).k = strArr;
                    return;
                }
                throw new IllegalArgumentException("Argument for @NotNull parameter 'result' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinSerializedIrArgumentVisitor$1.visitEnd must not be null");
        }
    }
}
