package androidx.collection;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJA\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0016\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001b\u001a\u00020\u001a8\u0000@\u0000X\u000e¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u0012\u0004\b\u001d\u0010\u0003R\u001c\u0010\u001f\u001a\u00020\u001e8\u0000@\u0000X\u000e¢\u0006\f\n\u0004\b\u001f\u0010 \u0012\u0004\b!\u0010\u0003R\u0016\u0010\"\u001a\u00020\r8\u0000@\u0000X\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\r8\u0000@\u0000X\u000e¢\u0006\u0006\n\u0004\b$\u0010#R\u0011\u0010&\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b%\u0010\u0014\u0001\u0001'¨\u0006("}, d2 = {"Landroidx/collection/FloatSet;", "", "<init>", "()V", "", "element", "", "contains", "(F)Z", "", "separator", "prefix", "postfix", "", "limit", "truncated", "", "joinToString", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;)Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "", "metadata", "[J", "getMetadata$annotations", "", "elements", "[F", "getElements$annotations", "_capacity", "I", "_size", "getCapacity", "capacity", "Landroidx/collection/MutableFloatSet;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FloatSet {
    public int _capacity;
    public int _size;
    public float[] elements;
    public long[] metadata;

    public /* synthetic */ FloatSet(e eVar) {
        this();
    }

    public static /* synthetic */ String joinToString$default(FloatSet floatSet, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, int i7, Object obj) {
        if (obj == null) {
            if ((i7 & 1) != 0) {
                charSequence = ArcCommonLog.TAG_COMMA;
            }
            if ((i7 & 2) != 0) {
                charSequence2 = "";
            }
            if ((i7 & 4) != 0) {
                charSequence3 = "";
            }
            if ((i7 & 8) != 0) {
                i2 = -1;
            }
            if ((i7 & 16) != 0) {
                charSequence4 = "...";
            }
            int i8 = i2;
            CharSequence charSequence5 = charSequence4;
            return floatSet.joinToString(charSequence, charSequence2, charSequence3, i8, charSequence5);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0065, code lost:
        if (((r6 & ((~r6) << 6)) & -9187201950435737472L) == 0) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0067, code lost:
        r10 = -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean contains(float r17) {
        /*
            r16 = this;
            r0 = r16
            int r1 = java.lang.Float.hashCode(r17)
            r2 = -862048943(0xffffffffcc9e2d51, float:-8.2930312E7)
            int r1 = r1 * r2
            int r2 = r1 << 16
            r1 = r1 ^ r2
            r2 = r1 & 127(0x7f, float:1.78E-43)
            int r3 = r0._capacity
            int r1 = r1 >>> 7
            r1 = r1 & r3
            r4 = 0
            r5 = r4
        L_0x0016:
            long[] r6 = r0.metadata
            int r7 = r1 >> 3
            r8 = r1 & 7
            int r8 = r8 << 3
            r9 = r6[r7]
            long r9 = r9 >>> r8
            r11 = 1
            int r7 = r7 + r11
            r6 = r6[r7]
            int r12 = 64 - r8
            long r6 = r6 << r12
            long r12 = (long) r8
            long r12 = -r12
            r8 = 63
            long r12 = r12 >> r8
            long r6 = r6 & r12
            long r6 = r6 | r9
            long r8 = (long) r2
            r12 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r8 = r8 * r12
            long r8 = r8 ^ r6
            long r12 = r8 - r12
            long r8 = ~r8
            long r8 = r8 & r12
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r12
        L_0x0041:
            r14 = 0
            int r10 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r10 == 0) goto L_0x005e
            int r10 = java.lang.Long.numberOfTrailingZeros(r8)
            int r10 = r10 >> 3
            int r10 = r10 + r1
            r10 = r10 & r3
            float[] r14 = r0.elements
            r14 = r14[r10]
            int r14 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r14 != 0) goto L_0x0058
            goto L_0x0068
        L_0x0058:
            r14 = 1
            long r14 = r8 - r14
            long r8 = r8 & r14
            goto L_0x0041
        L_0x005e:
            long r8 = ~r6
            r10 = 6
            long r8 = r8 << r10
            long r6 = r6 & r8
            long r6 = r6 & r12
            int r6 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x006c
            r10 = -1
        L_0x0068:
            if (r10 < 0) goto L_0x006b
            return r11
        L_0x006b:
            return r4
        L_0x006c:
            int r5 = r5 + 8
            int r1 = r1 + r5
            r1 = r1 & r3
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatSet.contains(float):boolean");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FloatSet)) {
            return false;
        }
        FloatSet floatSet = (FloatSet) obj;
        if (floatSet._size != this._size) {
            return false;
        }
        float[] fArr = this.elements;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j2 = jArr[i2];
                if ((((~j2) << 7) & j2 & -9187201950435737472L) != -9187201950435737472L) {
                    int i7 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i8 = 0; i8 < i7; i8++) {
                        if ((255 & j2) < 128 && !floatSet.contains(fArr[(i2 << 3) + i8])) {
                            return false;
                        }
                        j2 >>= 8;
                    }
                    if (i7 != 8) {
                        break;
                    }
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return true;
    }

    public final int getCapacity() {
        return this._capacity;
    }

    public int hashCode() {
        float[] fArr = this.elements;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return 0;
        }
        int i2 = 0;
        int i7 = 0;
        while (true) {
            long j2 = jArr[i2];
            if ((((~j2) << 7) & j2 & -9187201950435737472L) != -9187201950435737472L) {
                int i8 = 8 - ((~(i2 - length)) >>> 31);
                for (int i10 = 0; i10 < i8; i10++) {
                    if ((255 & j2) < 128) {
                        i7 = Float.hashCode(fArr[(i2 << 3) + i10]) + i7;
                    }
                    j2 >>= 8;
                }
                if (i8 != 8) {
                    return i7;
                }
            }
            if (i2 == length) {
                return i7;
            }
            i2++;
        }
    }

    public final String joinToString(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4) {
        CharSequence charSequence5 = charSequence;
        CharSequence charSequence6 = charSequence2;
        CharSequence charSequence7 = charSequence3;
        CharSequence charSequence8 = charSequence4;
        j.e(charSequence5, "separator");
        j.e(charSequence6, "prefix");
        j.e(charSequence7, "postfix");
        j.e(charSequence8, "truncated");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(charSequence6);
        float[] fArr = this.elements;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i7 = 0;
            int i8 = 0;
            loop0:
            while (true) {
                long j2 = jArr[i7];
                if ((((~j2) << 7) & j2 & -9187201950435737472L) != -9187201950435737472L) {
                    int i10 = 8 - ((~(i7 - length)) >>> 31);
                    for (int i11 = 0; i11 < i10; i11++) {
                        if ((255 & j2) < 128) {
                            float f = fArr[(i7 << 3) + i11];
                            if (i8 == i2) {
                                sb2.append(charSequence8);
                                break loop0;
                            }
                            if (i8 != 0) {
                                sb2.append(charSequence5);
                            }
                            sb2.append(f);
                            i8++;
                        } else {
                            int i12 = i2;
                        }
                        j2 >>= 8;
                    }
                    int i13 = i2;
                    if (i10 != 8) {
                        break;
                    }
                } else {
                    int i14 = i2;
                }
                if (i7 == length) {
                    break;
                }
                i7++;
            }
            String sb3 = sb2.toString();
            j.d(sb3, "StringBuilder().apply(builderAction).toString()");
            return sb3;
        }
        sb2.append(charSequence7);
        String sb32 = sb2.toString();
        j.d(sb32, "StringBuilder().apply(builderAction).toString()");
        return sb32;
    }

    public String toString() {
        return joinToString$default(this, (CharSequence) null, "[", "]", 0, (CharSequence) null, 25, (Object) null);
    }

    private FloatSet() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.elements = FloatSetKt.getEmptyFloatArray();
    }
}
