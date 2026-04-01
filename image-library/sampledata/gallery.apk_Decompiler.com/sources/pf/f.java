package pf;

import java.util.Arrays;
import kotlin.jvm.internal.j;
import nf.C1204a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends C1204a {
    public static final f g;

    /* renamed from: h  reason: collision with root package name */
    public static final f f5027h;
    public final boolean f;

    static {
        f fVar;
        f fVar2 = new f(false, new int[]{2, 0, 0});
        g = fVar2;
        int i2 = fVar2.f4952c;
        int i7 = fVar2.b;
        if (i7 == 1 && i2 == 9) {
            fVar = new f(false, new int[]{2, 0, 0});
        } else {
            fVar = new f(false, new int[]{i7, i2 + 1, 0});
        }
        f5027h = fVar;
        new f(false, new int[0]);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(boolean z, int[] iArr) {
        super(Arrays.copyOf(iArr, iArr.length));
        j.e(iArr, "versionArray");
        this.f = z;
    }

    public final boolean b(f fVar) {
        j.e(fVar, "metadataVersionFromLanguageVersion");
        f fVar2 = g;
        int i2 = this.f4952c;
        int i7 = this.b;
        if (i7 == 2 && i2 == 0 && fVar2.b == 1 && fVar2.f4952c == 8) {
            return true;
        }
        if (!this.f) {
            fVar2 = f5027h;
        }
        int i8 = fVar2.b;
        int i10 = fVar.b;
        if (i8 > i10 || (i8 >= i10 && fVar2.f4952c > fVar.f4952c)) {
            fVar = fVar2;
        }
        boolean z = false;
        if ((i7 == 1 && i2 == 0) || i7 == 0) {
            return false;
        }
        int i11 = fVar.b;
        if (i7 > i11 || (i7 >= i11 && i2 > fVar.f4952c)) {
            z = true;
        }
        return !z;
    }
}
