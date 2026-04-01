package androidx.collection;

import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0006\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\b\u0010\tJA\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\nH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001c\u001a\u00020\u001b8\u0000@\u0000X\u000e¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u0012\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\f\n\u0004\b \u0010!\u0012\u0004\b\"\u0010\u001f\u0001\u0001#¨\u0006$"}, d2 = {"Landroidx/collection/FloatList;", "", "", "initialCapacity", "<init>", "(I)V", "index", "", "get", "(I)F", "", "separator", "prefix", "postfix", "limit", "truncated", "", "joinToString", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;)Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "", "content", "[F", "getContent$annotations", "()V", "_size", "I", "get_size$annotations", "Landroidx/collection/MutableFloatList;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FloatList {
    public int _size;
    public float[] content;

    public /* synthetic */ FloatList(int i2, e eVar) {
        this(i2);
    }

    public static /* synthetic */ String joinToString$default(FloatList floatList, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, int i7, Object obj) {
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
            return floatList.joinToString(charSequence, charSequence2, charSequence3, i8, charSequence5);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r6 = (androidx.collection.FloatList) r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.collection.FloatList
            r1 = 0
            if (r0 == 0) goto L_0x002c
            androidx.collection.FloatList r6 = (androidx.collection.FloatList) r6
            int r0 = r6._size
            int r2 = r5._size
            if (r0 == r2) goto L_0x000e
            goto L_0x002c
        L_0x000e:
            float[] r5 = r5.content
            float[] r6 = r6.content
            Ge.e r0 = B1.a.Z(r1, r2)
            int r2 = r0.d
            int r0 = r0.e
            if (r2 > r0) goto L_0x002a
        L_0x001c:
            r3 = r5[r2]
            r4 = r6[r2]
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x0029
            if (r2 == r0) goto L_0x002a
            int r2 = r2 + 1
            goto L_0x001c
        L_0x0029:
            return r1
        L_0x002a:
            r5 = 1
            return r5
        L_0x002c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatList.equals(java.lang.Object):boolean");
    }

    public final float get(int i2) {
        if (i2 >= 0 && i2 < this._size) {
            return this.content[i2];
        }
        StringBuilder o2 = C0086a.o(i2, "Index ", " must be in 0..");
        o2.append(this._size - 1);
        throw new IndexOutOfBoundsException(o2.toString());
    }

    public int hashCode() {
        float[] fArr = this.content;
        int i2 = this._size;
        int i7 = 0;
        for (int i8 = 0; i8 < i2; i8++) {
            i7 += Float.hashCode(fArr[i8]) * 31;
        }
        return i7;
    }

    public final String joinToString(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4) {
        j.e(charSequence, "separator");
        j.e(charSequence2, "prefix");
        j.e(charSequence3, "postfix");
        j.e(charSequence4, "truncated");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(charSequence2);
        float[] fArr = this.content;
        int i7 = this._size;
        int i8 = 0;
        while (true) {
            if (i8 >= i7) {
                sb2.append(charSequence3);
                break;
            }
            float f = fArr[i8];
            if (i8 == i2) {
                sb2.append(charSequence4);
                break;
            }
            if (i8 != 0) {
                sb2.append(charSequence);
            }
            sb2.append(f);
            i8++;
        }
        String sb3 = sb2.toString();
        j.d(sb3, "StringBuilder().apply(builderAction).toString()");
        return sb3;
    }

    public String toString() {
        return joinToString$default(this, (CharSequence) null, "[", "]", 0, (CharSequence) null, 25, (Object) null);
    }

    private FloatList(int i2) {
        float[] fArr;
        if (i2 == 0) {
            fArr = FloatSetKt.getEmptyFloatArray();
        } else {
            fArr = new float[i2];
        }
        this.content = fArr;
    }
}
