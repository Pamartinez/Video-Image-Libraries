package androidx.work;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\b\u0002\u001a\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\u0007\u001a\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\u0005\u001a\u00020\tH\u0002¢\u0006\u0002\u0010\n\u001a\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\u0005\u001a\u00020\fH\u0002¢\u0006\u0002\u0010\r\u001a\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0005\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010\u0010\u001a\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\u0006\u0010\u0005\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0013\u001a\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\u0006\u0010\u0005\u001a\u00020\u0015H\u0002¢\u0006\u0002\u0010\u0016\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"TAG", "", "convertPrimitiveArray", "", "", "value", "", "([Z)[Ljava/lang/Boolean;", "", "", "([B)[Ljava/lang/Byte;", "", "", "([D)[Ljava/lang/Double;", "", "", "([F)[Ljava/lang/Float;", "", "", "([I)[Ljava/lang/Integer;", "", "", "([J)[Ljava/lang/Long;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Data_Kt {
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("Data");
        j.d(tagWithPrefix, "tagWithPrefix(\"Data\")");
        TAG = tagWithPrefix;
    }

    /* access modifiers changed from: private */
    public static final Boolean[] convertPrimitiveArray(boolean[] zArr) {
        int length = zArr.length;
        Boolean[] boolArr = new Boolean[length];
        for (int i2 = 0; i2 < length; i2++) {
            boolArr[i2] = Boolean.valueOf(zArr[i2]);
        }
        return boolArr;
    }

    /* access modifiers changed from: private */
    public static final Byte[] convertPrimitiveArray(byte[] bArr) {
        int length = bArr.length;
        Byte[] bArr2 = new Byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr2[i2] = Byte.valueOf(bArr[i2]);
        }
        return bArr2;
    }

    /* access modifiers changed from: private */
    public static final Integer[] convertPrimitiveArray(int[] iArr) {
        int length = iArr.length;
        Integer[] numArr = new Integer[length];
        for (int i2 = 0; i2 < length; i2++) {
            numArr[i2] = Integer.valueOf(iArr[i2]);
        }
        return numArr;
    }

    /* access modifiers changed from: private */
    public static final Long[] convertPrimitiveArray(long[] jArr) {
        int length = jArr.length;
        Long[] lArr = new Long[length];
        for (int i2 = 0; i2 < length; i2++) {
            lArr[i2] = Long.valueOf(jArr[i2]);
        }
        return lArr;
    }

    /* access modifiers changed from: private */
    public static final Float[] convertPrimitiveArray(float[] fArr) {
        int length = fArr.length;
        Float[] fArr2 = new Float[length];
        for (int i2 = 0; i2 < length; i2++) {
            fArr2[i2] = Float.valueOf(fArr[i2]);
        }
        return fArr2;
    }

    /* access modifiers changed from: private */
    public static final Double[] convertPrimitiveArray(double[] dArr) {
        int length = dArr.length;
        Double[] dArr2 = new Double[length];
        for (int i2 = 0; i2 < length; i2++) {
            dArr2[i2] = Double.valueOf(dArr[i2]);
        }
        return dArr2;
    }
}
