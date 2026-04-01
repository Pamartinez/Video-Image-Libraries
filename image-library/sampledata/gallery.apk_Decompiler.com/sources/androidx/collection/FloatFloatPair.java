package androidx.collection;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import kotlin.Metadata;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\b@\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0000\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0016\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\b\u001a\u00020\u00078\u0000X\u0004¢\u0006\f\n\u0004\b\b\u0010\u0017\u0012\u0004\b\u0018\u0010\u0019\u0001\b\u0001\u00020\u0007¨\u0006\u001a"}, d2 = {"Landroidx/collection/FloatFloatPair;", "", "", "first", "second", "constructor-impl", "(FF)J", "", "packedValue", "(J)J", "", "toString-impl", "(J)Ljava/lang/String;", "toString", "", "hashCode-impl", "(J)I", "hashCode", "other", "", "equals-impl", "(JLjava/lang/Object;)Z", "equals", "J", "getPackedValue$annotations", "()V", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FloatFloatPair {
    public final long packedValue;

    private /* synthetic */ FloatFloatPair(long j2) {
        this.packedValue = j2;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ FloatFloatPair m0boximpl(long j2) {
        return new FloatFloatPair(j2);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static long m2constructorimpl(long j2) {
        return j2;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m3equalsimpl(long j2, Object obj) {
        if ((obj instanceof FloatFloatPair) && j2 == ((FloatFloatPair) obj).m6unboximpl()) {
            return true;
        }
        return false;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m4hashCodeimpl(long j2) {
        return Long.hashCode(j2);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m5toStringimpl(long j2) {
        return "(" + Float.intBitsToFloat((int) (j2 >> 32)) + ArcCommonLog.TAG_COMMA + Float.intBitsToFloat((int) (j2 & 4294967295L)) + ')';
    }

    public boolean equals(Object obj) {
        return m3equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m4hashCodeimpl(this.packedValue);
    }

    public String toString() {
        return m5toStringimpl(this.packedValue);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m6unboximpl() {
        return this.packedValue;
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static long m1constructorimpl(float f, float f5) {
        long floatToRawIntBits = (long) Float.floatToRawIntBits(f);
        return m2constructorimpl((((long) Float.floatToRawIntBits(f5)) & 4294967295L) | (floatToRawIntBits << 32));
    }
}
