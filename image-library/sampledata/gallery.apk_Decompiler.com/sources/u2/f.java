package U2;

import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends Nf.f {

    /* renamed from: h  reason: collision with root package name */
    public static final f f863h;
    public int[] e;
    public int f;
    public boolean g;

    static {
        f fVar = new f(0);
        f863h = fVar;
        fVar.d = false;
    }

    public f(int i2) {
        super(true);
        try {
            this.e = new int[i2];
            this.f = 0;
            this.g = true;
        } catch (NegativeArraySizeException unused) {
            throw new IllegalArgumentException("size < 0");
        }
    }

    public final void d(int i2) {
        c();
        int i7 = this.f;
        int[] iArr = this.e;
        boolean z = false;
        if (i7 == iArr.length) {
            int[] iArr2 = new int[(((i7 * 3) / 2) + 10)];
            System.arraycopy(iArr, 0, iArr2, 0, i7);
            this.e = iArr2;
        }
        int[] iArr3 = this.e;
        int i8 = this.f;
        int i10 = i8 + 1;
        this.f = i10;
        iArr3[i8] = i2;
        if (this.g && i10 > 1) {
            if (i2 >= iArr3[i8 - 1]) {
                z = true;
            }
            this.g = z;
        }
    }

    public final int e(int i2) {
        if (i2 < this.f) {
            try {
                return this.e[i2];
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new IndexOutOfBoundsException("n < 0");
            }
        } else {
            throw new IndexOutOfBoundsException("n >= size()");
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (this.g != fVar.g || this.f != fVar.f) {
            return false;
        }
        for (int i2 = 0; i2 < this.f; i2++) {
            if (this.e[i2] != fVar.e[i2]) {
                return false;
            }
        }
        return true;
    }

    public final void f(int i2, int i7) {
        c();
        if (i2 < this.f) {
            try {
                this.e[i2] = i7;
                this.g = false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                if (i2 < 0) {
                    throw new IllegalArgumentException("n < 0");
                }
            }
        } else {
            throw new IndexOutOfBoundsException("n >= size()");
        }
    }

    public final int hashCode() {
        int i2 = 0;
        for (int i7 = 0; i7 < this.f; i7++) {
            i2 = (i2 * 31) + this.e[i7];
        }
        return i2;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer((this.f * 5) + 10);
        stringBuffer.append('{');
        for (int i2 = 0; i2 < this.f; i2++) {
            if (i2 != 0) {
                stringBuffer.append(ArcCommonLog.TAG_COMMA);
            }
            stringBuffer.append(this.e[i2]);
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
