package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.photoremaster.sdk.common.Constants;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1192j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B#\b\u0016\u0012\u0018\u0010\b\u001a\u0014\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0006\b\u0001\u0012\u00028\u0001\u0018\u00010\u0000¢\u0006\u0004\b\u0006\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000e\u0010\u0007J\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0001H\u0001¢\u0006\u0004\b\u0016\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0018\u0010\u0012J\u001a\u0010\u0019\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\u001c\u001a\u00028\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001b\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u001e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b!\u0010 J\u001f\u0010\"\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0010H\u0016¢\u0006\u0004\b$\u0010%J!\u0010&\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b&\u0010\u001dJ'\u0010'\u001a\u00020\n2\u0016\u0010\b\u001a\u0012\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0006\b\u0001\u0012\u00028\u00010\u0000H\u0016¢\u0006\u0004\b'\u0010\tJ!\u0010(\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b(\u0010\u001dJ\u0019\u0010)\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b)\u0010\u001aJ\u001f\u0010)\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b+\u0010 J!\u0010,\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b,\u0010\u001dJ'\u0010,\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010-\u001a\u00028\u00012\u0006\u0010.\u001a\u00028\u0001H\u0016¢\u0006\u0004\b,\u0010/J\u000f\u00100\u001a\u00020\u0004H\u0016¢\u0006\u0004\b0\u00101J\u001a\u00103\u001a\u00020\u00102\b\u00102\u001a\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0004\b3\u0010\u0012J\u000f\u00104\u001a\u00020\u0004H\u0016¢\u0006\u0004\b4\u00101J\u000f\u00106\u001a\u000205H\u0016¢\u0006\u0004\b6\u00107J\u001f\u00109\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u00108\u001a\u00020\u0004H\u0002¢\u0006\u0004\b9\u0010:J\u000f\u0010;\u001a\u00020\u0004H\u0002¢\u0006\u0004\b;\u00101R\u0016\u0010=\u001a\u00020<8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u001e\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030?8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0016\u00100\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b0\u0010B¨\u0006C"}, d2 = {"Landroidx/collection/SimpleArrayMap;", "K", "V", "", "", "capacity", "<init>", "(I)V", "map", "(Landroidx/collection/SimpleArrayMap;)V", "Lme/x;", "clear", "()V", "minimumCapacity", "ensureCapacity", "key", "", "containsKey", "(Ljava/lang/Object;)Z", "indexOfKey", "(Ljava/lang/Object;)I", "value", "__restricted$indexOfValue", "indexOfValue", "containsValue", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "defaultValue", "getOrDefault", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "index", "keyAt", "(I)Ljava/lang/Object;", "valueAt", "setValueAt", "(ILjava/lang/Object;)Ljava/lang/Object;", "isEmpty", "()Z", "put", "putAll", "putIfAbsent", "remove", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "removeAt", "replace", "oldValue", "newValue", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "size", "()I", "other", "equals", "hashCode", "", "toString", "()Ljava/lang/String;", "hash", "indexOf", "(Ljava/lang/Object;I)I", "indexOfNull", "", "hashes", "[I", "", "array", "[Ljava/lang/Object;", "I", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleArrayMap<K, V> {
    private Object[] array;
    private int[] hashes;
    private int size;

    public SimpleArrayMap() {
        this(0, 1, (e) null);
    }

    private final int indexOf(K k, int i2) {
        int i7 = this.size;
        if (i7 == 0) {
            return -1;
        }
        int binarySearch = ContainerHelpersKt.binarySearch(this.hashes, i7, i2);
        if (binarySearch < 0 || j.a(k, this.array[binarySearch << 1])) {
            return binarySearch;
        }
        int i8 = binarySearch + 1;
        while (i8 < i7 && this.hashes[i8] == i2) {
            if (j.a(k, this.array[i8 << 1])) {
                return i8;
            }
            i8++;
        }
        int i10 = binarySearch - 1;
        while (i10 >= 0 && this.hashes[i10] == i2) {
            if (j.a(k, this.array[i10 << 1])) {
                return i10;
            }
            i10--;
        }
        return ~i8;
    }

    private final int indexOfNull() {
        int i2 = this.size;
        if (i2 == 0) {
            return -1;
        }
        int binarySearch = ContainerHelpersKt.binarySearch(this.hashes, i2, 0);
        if (binarySearch < 0 || this.array[binarySearch << 1] == null) {
            return binarySearch;
        }
        int i7 = binarySearch + 1;
        while (i7 < i2 && this.hashes[i7] == 0) {
            if (this.array[i7 << 1] == null) {
                return i7;
            }
            i7++;
        }
        int i8 = binarySearch - 1;
        while (i8 >= 0 && this.hashes[i8] == 0) {
            if (this.array[i8 << 1] == null) {
                return i8;
            }
            i8--;
        }
        return ~i7;
    }

    public final int __restricted$indexOfValue(V v) {
        int i2 = this.size * 2;
        Object[] objArr = this.array;
        if (v == null) {
            for (int i7 = 1; i7 < i2; i7 += 2) {
                if (objArr[i7] == null) {
                    return i7 >> 1;
                }
            }
            return -1;
        }
        for (int i8 = 1; i8 < i2; i8 += 2) {
            if (v.equals(objArr[i8])) {
                return i8 >> 1;
            }
        }
        return -1;
    }

    public void clear() {
        if (this.size > 0) {
            this.hashes = ContainerHelpersKt.EMPTY_INTS;
            this.array = ContainerHelpersKt.EMPTY_OBJECTS;
            this.size = 0;
        }
        if (this.size > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(K k) {
        if (indexOfKey(k) >= 0) {
            return true;
        }
        return false;
    }

    public boolean containsValue(V v) {
        if (__restricted$indexOfValue(v) >= 0) {
            return true;
        }
        return false;
    }

    public void ensureCapacity(int i2) {
        int i7 = this.size;
        int[] iArr = this.hashes;
        if (iArr.length < i2) {
            int[] copyOf = Arrays.copyOf(iArr, i2);
            j.d(copyOf, "copyOf(this, newSize)");
            this.hashes = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.array, i2 * 2);
            j.d(copyOf2, "copyOf(this, newSize)");
            this.array = copyOf2;
        }
        if (this.size != i7) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof SimpleArrayMap) {
                if (size() != ((SimpleArrayMap) obj).size()) {
                    return false;
                }
                SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
                int i2 = this.size;
                for (int i7 = 0; i7 < i2; i7++) {
                    Object keyAt = keyAt(i7);
                    Object valueAt = valueAt(i7);
                    Object obj2 = simpleArrayMap.get(keyAt);
                    if (valueAt == null) {
                        if (obj2 != null || !simpleArrayMap.containsKey(keyAt)) {
                            return false;
                        }
                    } else if (!valueAt.equals(obj2)) {
                        return false;
                    }
                }
                return true;
            } else if (!(obj instanceof Map) || size() != ((Map) obj).size()) {
                return false;
            } else {
                int i8 = this.size;
                for (int i10 = 0; i10 < i8; i10++) {
                    Object keyAt2 = keyAt(i10);
                    Object valueAt2 = valueAt(i10);
                    Object obj3 = ((Map) obj).get(keyAt2);
                    if (valueAt2 == null) {
                        if (obj3 != null || !((Map) obj).containsKey(keyAt2)) {
                            return false;
                        }
                    } else if (!valueAt2.equals(obj3)) {
                        return false;
                    }
                }
                return true;
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public V get(K k) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey >= 0) {
            return this.array[(indexOfKey << 1) + 1];
        }
        return null;
    }

    public V getOrDefault(Object obj, V v) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return this.array[(indexOfKey << 1) + 1];
        }
        return v;
    }

    public int hashCode() {
        int i2;
        int[] iArr = this.hashes;
        Object[] objArr = this.array;
        int i7 = this.size;
        int i8 = 1;
        int i10 = 0;
        int i11 = 0;
        while (i10 < i7) {
            Object obj = objArr[i8];
            int i12 = iArr[i10];
            if (obj != null) {
                i2 = obj.hashCode();
            } else {
                i2 = 0;
            }
            i11 += i2 ^ i12;
            i10++;
            i8 += 2;
        }
        return i11;
    }

    public int indexOfKey(K k) {
        if (k == null) {
            return indexOfNull();
        }
        return indexOf(k, k.hashCode());
    }

    public boolean isEmpty() {
        if (this.size <= 0) {
            return true;
        }
        return false;
    }

    public K keyAt(int i2) {
        if (i2 >= 0 && i2 < this.size) {
            return this.array[i2 << 1];
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Expected index to be within 0..size()-1, but was ").toString());
    }

    public V put(K k, V v) {
        int i2;
        int i7;
        int i8 = this.size;
        if (k != null) {
            i2 = k.hashCode();
        } else {
            i2 = 0;
        }
        if (k != null) {
            i7 = indexOf(k, i2);
        } else {
            i7 = indexOfNull();
        }
        if (i7 >= 0) {
            int i10 = (i7 << 1) + 1;
            V[] vArr = this.array;
            V v6 = vArr[i10];
            vArr[i10] = v;
            return v6;
        }
        int i11 = ~i7;
        int[] iArr = this.hashes;
        if (i8 >= iArr.length) {
            int i12 = 8;
            if (i8 >= 8) {
                i12 = (i8 >> 1) + i8;
            } else if (i8 < 4) {
                i12 = 4;
            }
            int[] copyOf = Arrays.copyOf(iArr, i12);
            j.d(copyOf, "copyOf(this, newSize)");
            this.hashes = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.array, i12 << 1);
            j.d(copyOf2, "copyOf(this, newSize)");
            this.array = copyOf2;
            if (i8 != this.size) {
                throw new ConcurrentModificationException();
            }
        }
        if (i11 < i8) {
            int[] iArr2 = this.hashes;
            int i13 = i11 + 1;
            C1192j.f0(i13, i11, i8, iArr2, iArr2);
            Object[] objArr = this.array;
            C1192j.g0(i13 << 1, i11 << 1, this.size << 1, objArr, objArr);
        }
        int i14 = this.size;
        if (i8 == i14) {
            int[] iArr3 = this.hashes;
            if (i11 < iArr3.length) {
                iArr3[i11] = i2;
                Object[] objArr2 = this.array;
                int i15 = i11 << 1;
                objArr2[i15] = k;
                objArr2[i15 + 1] = v;
                this.size = i14 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public void putAll(SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        j.e(simpleArrayMap, "map");
        int i2 = simpleArrayMap.size;
        ensureCapacity(this.size + i2);
        if (this.size != 0) {
            for (int i7 = 0; i7 < i2; i7++) {
                put(simpleArrayMap.keyAt(i7), simpleArrayMap.valueAt(i7));
            }
        } else if (i2 > 0) {
            C1192j.f0(0, 0, i2, simpleArrayMap.hashes, this.hashes);
            C1192j.g0(0, 0, i2 << 1, simpleArrayMap.array, this.array);
            this.size = i2;
        }
    }

    public V putIfAbsent(K k, V v) {
        V v6 = get(k);
        if (v6 == null) {
            return put(k, v);
        }
        return v6;
    }

    public V remove(K k) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey >= 0) {
            return removeAt(indexOfKey);
        }
        return null;
    }

    public V removeAt(int i2) {
        int i7;
        if (i2 < 0 || i2 >= (i7 = this.size)) {
            throw new IllegalArgumentException(C0086a.i(i2, "Expected index to be within 0..size()-1, but was ").toString());
        }
        V[] vArr = this.array;
        int i8 = i2 << 1;
        V v = vArr[i8 + 1];
        if (i7 <= 1) {
            clear();
            return v;
        }
        int i10 = i7 - 1;
        int[] iArr = this.hashes;
        int i11 = 8;
        if (iArr.length <= 8 || i7 >= iArr.length / 3) {
            if (i2 < i10) {
                int i12 = i2 + 1;
                C1192j.f0(i2, i12, i7, iArr, iArr);
                Object[] objArr = this.array;
                C1192j.g0(i8, i12 << 1, i7 << 1, objArr, objArr);
            }
            Object[] objArr2 = this.array;
            int i13 = i10 << 1;
            objArr2[i13] = null;
            objArr2[i13 + 1] = null;
        } else {
            if (i7 > 8) {
                i11 = i7 + (i7 >> 1);
            }
            int[] copyOf = Arrays.copyOf(iArr, i11);
            j.d(copyOf, "copyOf(this, newSize)");
            this.hashes = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.array, i11 << 1);
            j.d(copyOf2, "copyOf(this, newSize)");
            this.array = copyOf2;
            if (i7 == this.size) {
                if (i2 > 0) {
                    C1192j.f0(0, 0, i2, iArr, this.hashes);
                    C1192j.g0(0, 0, i8, vArr, this.array);
                }
                if (i2 < i10) {
                    int i14 = i2 + 1;
                    C1192j.f0(i2, i14, i7, iArr, this.hashes);
                    C1192j.g0(i8, i14 << 1, i7 << 1, vArr, this.array);
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i7 == this.size) {
            this.size = i10;
            return v;
        }
        throw new ConcurrentModificationException();
    }

    public V replace(K k, V v) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey >= 0) {
            return setValueAt(indexOfKey, v);
        }
        return null;
    }

    public V setValueAt(int i2, V v) {
        if (i2 < 0 || i2 >= this.size) {
            throw new IllegalArgumentException(C0086a.i(i2, "Expected index to be within 0..size()-1, but was ").toString());
        }
        int i7 = (i2 << 1) + 1;
        V[] vArr = this.array;
        V v6 = vArr[i7];
        vArr[i7] = v;
        return v6;
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        if (isEmpty()) {
            return Constants.EMPTY_JSON_STRING;
        }
        StringBuilder sb2 = new StringBuilder(this.size * 28);
        sb2.append('{');
        int i2 = this.size;
        for (int i7 = 0; i7 < i2; i7++) {
            if (i7 > 0) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
            Object keyAt = keyAt(i7);
            if (keyAt != sb2) {
                sb2.append(keyAt);
            } else {
                sb2.append("(this Map)");
            }
            sb2.append('=');
            Object valueAt = valueAt(i7);
            if (valueAt != sb2) {
                sb2.append(valueAt);
            } else {
                sb2.append("(this Map)");
            }
        }
        sb2.append('}');
        String sb3 = sb2.toString();
        j.d(sb3, "StringBuilder(capacity).…builderAction).toString()");
        return sb3;
    }

    public V valueAt(int i2) {
        if (i2 >= 0 && i2 < this.size) {
            return this.array[(i2 << 1) + 1];
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Expected index to be within 0..size()-1, but was ").toString());
    }

    public SimpleArrayMap(int i2) {
        int[] iArr;
        Object[] objArr;
        if (i2 == 0) {
            iArr = ContainerHelpersKt.EMPTY_INTS;
        } else {
            iArr = new int[i2];
        }
        this.hashes = iArr;
        if (i2 == 0) {
            objArr = ContainerHelpersKt.EMPTY_OBJECTS;
        } else {
            objArr = new Object[(i2 << 1)];
        }
        this.array = objArr;
    }

    public boolean remove(K k, V v) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey < 0 || !j.a(v, valueAt(indexOfKey))) {
            return false;
        }
        removeAt(indexOfKey);
        return true;
    }

    public boolean replace(K k, V v, V v6) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey < 0 || !j.a(v, valueAt(indexOfKey))) {
            return false;
        }
        setValueAt(indexOfKey, v6);
        return true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SimpleArrayMap(int i2, int i7, e eVar) {
        this((i7 & 1) != 0 ? 0 : i2);
    }

    public SimpleArrayMap(SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        this(0, 1, (e) null);
        if (simpleArrayMap != null) {
            putAll(simpleArrayMap);
        }
    }
}
