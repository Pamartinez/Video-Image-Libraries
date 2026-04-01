package ig;

import Be.a;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kg.C1139u;
import kotlin.jvm.internal.j;
import me.o;
import me.q;
import me.s;
import me.v;
import ne.C1187e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class i implements Iterator, a {
    public final /* synthetic */ int d;
    public int e;
    public final Object f;

    public /* synthetic */ i(int i2, Object obj) {
        this.d = i2;
        this.f = obj;
    }

    public final boolean hasNext() {
        switch (this.d) {
            case 0:
                if (this.e > 0) {
                    return true;
                }
                return false;
            case 1:
                if (this.e > 0) {
                    return true;
                }
                return false;
            case 2:
                if (this.e < ((Object[]) this.f).length) {
                    return true;
                }
                return false;
            case 3:
                if (this.e < ((byte[]) this.f).length) {
                    return true;
                }
                return false;
            case 4:
                if (this.e < ((int[]) this.f).length) {
                    return true;
                }
                return false;
            case 5:
                if (this.e < ((long[]) this.f).length) {
                    return true;
                }
                return false;
            case 6:
                if (this.e < ((short[]) this.f).length) {
                    return true;
                }
                return false;
            default:
                if (this.e < ((C1187e) this.f).size()) {
                    return true;
                }
                return false;
        }
    }

    public final Object next() {
        switch (this.d) {
            case 0:
                f fVar = (f) this.f;
                int e7 = fVar.e();
                int i2 = this.e;
                this.e = i2 - 1;
                return fVar.h(e7 - i2);
            case 1:
                C1139u uVar = (C1139u) this.f;
                int i7 = uVar.f4680c;
                int i8 = this.e;
                this.e = i8 - 1;
                return uVar.e[i7 - i8];
            case 2:
                try {
                    int i10 = this.e;
                    this.e = i10 + 1;
                    return ((Object[]) this.f)[i10];
                } catch (ArrayIndexOutOfBoundsException e8) {
                    this.e--;
                    throw new NoSuchElementException(e8.getMessage());
                }
            case 3:
                int i11 = this.e;
                byte[] bArr = (byte[]) this.f;
                if (i11 < bArr.length) {
                    this.e = i11 + 1;
                    return new o(bArr[i11]);
                }
                throw new NoSuchElementException(String.valueOf(this.e));
            case 4:
                int i12 = this.e;
                int[] iArr = (int[]) this.f;
                if (i12 < iArr.length) {
                    this.e = i12 + 1;
                    return new q(iArr[i12]);
                }
                throw new NoSuchElementException(String.valueOf(this.e));
            case 5:
                int i13 = this.e;
                long[] jArr = (long[]) this.f;
                if (i13 < jArr.length) {
                    this.e = i13 + 1;
                    return new s(jArr[i13]);
                }
                throw new NoSuchElementException(String.valueOf(this.e));
            case 6:
                int i14 = this.e;
                short[] sArr = (short[]) this.f;
                if (i14 < sArr.length) {
                    this.e = i14 + 1;
                    return new v(sArr[i14]);
                }
                throw new NoSuchElementException(String.valueOf(this.e));
            default:
                if (hasNext()) {
                    int i15 = this.e;
                    this.e = i15 + 1;
                    return ((C1187e) this.f).get(i15);
                }
                throw new NoSuchElementException();
        }
    }

    public final void remove() {
        switch (this.d) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 2:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 3:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 4:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 5:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 6:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public i(Object[] objArr) {
        this.d = 2;
        j.e(objArr, "array");
        this.f = objArr;
    }

    public i(f fVar) {
        this.d = 0;
        this.f = fVar;
        this.e = fVar.e();
    }

    public i(C1139u uVar) {
        this.d = 1;
        this.f = uVar;
        this.e = uVar.f4680c;
    }
}
