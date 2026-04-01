package ne;

import java.util.RandomAccess;
import kotlin.jvm.internal.j;

/* renamed from: ne.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1193k extends C1187e implements RandomAccess {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C1193k(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final boolean contains(Object obj) {
        switch (this.d) {
            case 0:
                if (!(obj instanceof Integer)) {
                    return false;
                }
                return C1192j.c0(((Number) obj).intValue(), (int[]) this.e);
            default:
                if (!(obj instanceof Float)) {
                    return false;
                }
                float floatValue = ((Number) obj).floatValue();
                for (float floatToIntBits : (float[]) this.e) {
                    if (Float.floatToIntBits(floatToIntBits) == Float.floatToIntBits(floatValue)) {
                        return true;
                    }
                }
                return false;
        }
    }

    public final Object get(int i2) {
        switch (this.d) {
            case 0:
                return Integer.valueOf(((int[]) this.e)[i2]);
            default:
                return Float.valueOf(((float[]) this.e)[i2]);
        }
    }

    public final int indexOf(Object obj) {
        switch (this.d) {
            case 0:
                if (!(obj instanceof Integer)) {
                    return -1;
                }
                int intValue = ((Number) obj).intValue();
                int[] iArr = (int[]) this.e;
                j.e(iArr, "<this>");
                int length = iArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    if (intValue == iArr[i2]) {
                        return i2;
                    }
                }
                return -1;
            default:
                if (!(obj instanceof Float)) {
                    return -1;
                }
                float floatValue = ((Number) obj).floatValue();
                float[] fArr = (float[]) this.e;
                int length2 = fArr.length;
                for (int i7 = 0; i7 < length2; i7++) {
                    if (Float.floatToIntBits(fArr[i7]) == Float.floatToIntBits(floatValue)) {
                        return i7;
                    }
                }
                return -1;
        }
    }

    public final boolean isEmpty() {
        switch (this.d) {
            case 0:
                if (((int[]) this.e).length == 0) {
                    return true;
                }
                return false;
            default:
                if (((float[]) this.e).length == 0) {
                    return true;
                }
                return false;
        }
    }

    public final int lastIndexOf(Object obj) {
        switch (this.d) {
            case 0:
                if (!(obj instanceof Integer)) {
                    return -1;
                }
                int intValue = ((Number) obj).intValue();
                int[] iArr = (int[]) this.e;
                j.e(iArr, "<this>");
                int length = iArr.length - 1;
                if (length < 0) {
                    return -1;
                }
                while (true) {
                    int i2 = length - 1;
                    if (intValue == iArr[length]) {
                        return length;
                    }
                    if (i2 < 0) {
                        return -1;
                    }
                    length = i2;
                }
            default:
                if (!(obj instanceof Float)) {
                    return -1;
                }
                float floatValue = ((Number) obj).floatValue();
                float[] fArr = (float[]) this.e;
                int length2 = fArr.length - 1;
                if (length2 < 0) {
                    return -1;
                }
                while (true) {
                    int i7 = length2 - 1;
                    if (Float.floatToIntBits(fArr[length2]) == Float.floatToIntBits(floatValue)) {
                        return length2;
                    }
                    if (i7 < 0) {
                        return -1;
                    }
                    length2 = i7;
                }
        }
    }

    public final int p() {
        switch (this.d) {
            case 0:
                return ((int[]) this.e).length;
            default:
                return ((float[]) this.e).length;
        }
    }
}
