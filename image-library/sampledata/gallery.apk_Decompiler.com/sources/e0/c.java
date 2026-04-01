package E0;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final float[] f126a;
    public final int[] b;

    public c(int[] iArr, float[] fArr) {
        this.f126a = fArr;
        this.b = iArr;
    }

    public final void a(c cVar) {
        int i2 = 0;
        while (true) {
            int[] iArr = cVar.b;
            if (i2 < iArr.length) {
                this.f126a[i2] = cVar.f126a[i2];
                this.b[i2] = iArr[i2];
                i2++;
            } else {
                return;
            }
        }
    }

    public final c b(float[] fArr) {
        int i2;
        int[] iArr = new int[fArr.length];
        for (int i7 = 0; i7 < fArr.length; i7++) {
            float f = fArr[i7];
            float[] fArr2 = this.f126a;
            int binarySearch = Arrays.binarySearch(fArr2, f);
            int[] iArr2 = this.b;
            if (binarySearch >= 0) {
                i2 = iArr2[binarySearch];
            } else {
                int i8 = -(binarySearch + 1);
                if (i8 == 0) {
                    i2 = iArr2[0];
                } else if (i8 == iArr2.length - 1) {
                    i2 = iArr2[iArr2.length - 1];
                } else {
                    int i10 = i8 - 1;
                    float f5 = fArr2[i10];
                    i2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.u((f - f5) / (fArr2[i8] - f5), iArr2[i10], iArr2[i8]);
                }
            }
            iArr[i7] = i2;
        }
        return new c(iArr, fArr);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && c.class == obj.getClass()) {
            c cVar = (c) obj;
            if (!Arrays.equals(this.f126a, cVar.f126a) || !Arrays.equals(this.b, cVar.b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b) + (Arrays.hashCode(this.f126a) * 31);
    }
}
