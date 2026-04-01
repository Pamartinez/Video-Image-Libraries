package L2;

import T2.b;
import U2.d;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s {

    /* renamed from: a  reason: collision with root package name */
    public final r[] f414a;
    public final b b;

    /* JADX WARNING: type inference failed for: r0v2, types: [U2.d, T2.b] */
    public s(r[] rVarArr) {
        this.f414a = (r[]) rVarArr.clone();
        this.b = new d(rVarArr.length);
        for (int i2 = 0; i2 < rVarArr.length; i2++) {
            this.b.e(i2, rVarArr[i2].b);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof s) || !Arrays.equals(((s) obj).f414a, this.f414a)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f414a);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        int i2 = 0;
        while (true) {
            r[] rVarArr = this.f414a;
            if (i2 >= rVarArr.length) {
                return sb2.toString();
            }
            if (i2 > 0) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
            sb2.append(rVarArr[i2]);
            i2++;
        }
    }
}
