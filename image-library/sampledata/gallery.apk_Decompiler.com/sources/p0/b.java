package P0;

import D0.e;
import U2.a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public boolean f569a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f570c;
    public int d;
    public Object e;
    public Object f;

    public b() {
        this(new byte[1000], true);
    }

    public static int e(String str) {
        int charAt = str.charAt(0);
        int length = str.length();
        for (int i2 = 1; i2 < length; i2++) {
            charAt = (charAt * 31) + str.charAt(i2);
        }
        return charAt;
    }

    public static void i() {
        throw new IndexOutOfBoundsException("attempt to write past the end");
    }

    public void a(int i2) {
        int i7 = i2 - 1;
        if (i2 < 0 || (i2 & i7) != 0) {
            throw new IllegalArgumentException("bogus alignment");
        }
        int i8 = (this.b + i7) & (~i7);
        if (this.f569a) {
            g(i8);
        } else if (i8 > ((byte[]) this.e).length) {
            i();
            throw null;
        }
        this.b = i8;
    }

    public void b(int i2, String str) {
        int i7;
        if (((ArrayList) this.f) != null) {
            f();
            int size = ((ArrayList) this.f).size();
            if (size == 0) {
                i7 = 0;
            } else {
                i7 = ((a) ((ArrayList) this.f).get(size - 1)).f860a;
            }
            int i8 = this.b;
            if (i7 <= i8) {
                i7 = i8;
            }
            ((ArrayList) this.f).add(new a(i7, i2 + i7, str));
        }
    }

    public void c(String str) {
        if (((ArrayList) this.f) != null) {
            f();
            ((ArrayList) this.f).add(new a(this.b, Integer.MAX_VALUE, str));
        }
    }

    public boolean d() {
        if (((ArrayList) this.f) != null) {
            return true;
        }
        return false;
    }

    public void f() {
        int size;
        ArrayList arrayList = (ArrayList) this.f;
        if (arrayList != null && (size = arrayList.size()) != 0) {
            a aVar = (a) ((ArrayList) this.f).get(size - 1);
            int i2 = this.b;
            if (aVar.f860a == Integer.MAX_VALUE) {
                aVar.f860a = i2;
            }
        }
    }

    public void g(int i2) {
        byte[] bArr = (byte[]) this.e;
        if (bArr.length < i2) {
            byte[] bArr2 = new byte[((i2 * 2) + 1000)];
            System.arraycopy(bArr, 0, bArr2, 0, this.b);
            this.e = bArr2;
        }
    }

    public void h(String str) {
        int length = str.length();
        if (length >= 1) {
            int e7 = e(str) & this.d;
            String str2 = ((String[]) this.e)[e7];
            if (str2 != null) {
                if (str2.length() == length) {
                    int i2 = 0;
                    while (i2 < length && str2.charAt(i2) == str.charAt(i2)) {
                        i2++;
                    }
                    if (i2 == length) {
                        return;
                    }
                }
                e eVar = ((e[]) this.f)[e7 >> 1];
                if (eVar != null) {
                    String str3 = (String) eVar.e;
                    Object obj = eVar.f;
                    while (true) {
                        e eVar2 = (e) obj;
                        if (str3.equals(str)) {
                            break;
                        } else if (eVar2 == null) {
                            str3 = null;
                            break;
                        } else {
                            str3 = (String) eVar2.e;
                            obj = eVar2.f;
                        }
                    }
                    if (str3 != null) {
                        return;
                    }
                }
            }
            int i7 = this.b;
            int i8 = this.f570c;
            if (i7 >= i8) {
                int i10 = r2 + r2;
                e[] eVarArr = (e[]) this.f;
                this.e = new String[i10];
                this.f = new e[(i10 >> 1)];
                this.d = i10 - 1;
                this.f570c = i8 + i8;
                int i11 = 0;
                for (String str4 : (String[]) this.e) {
                    if (str4 != null) {
                        i11++;
                        int e8 = e(str4) & this.d;
                        String[] strArr = (String[]) this.e;
                        if (strArr[e8] == null) {
                            strArr[e8] = str4;
                        } else {
                            int i12 = e8 >> 1;
                            e[] eVarArr2 = (e[]) this.f;
                            eVarArr2[i12] = new e(14, (Object) str4, (Object) eVarArr2[i12]);
                        }
                    }
                }
                int i13 = r2 >> 1;
                for (int i14 = 0; i14 < i13; i14++) {
                    for (e eVar3 = eVarArr[i14]; eVar3 != null; eVar3 = (e) eVar3.f) {
                        i11++;
                        String str5 = (String) eVar3.e;
                        int e9 = e(str5) & this.d;
                        String[] strArr2 = (String[]) this.e;
                        if (strArr2[e9] == null) {
                            strArr2[e9] = str5;
                        } else {
                            int i15 = e9 >> 1;
                            e[] eVarArr3 = (e[]) this.f;
                            eVarArr3[i15] = new e(14, (Object) str5, (Object) eVarArr3[i15]);
                        }
                    }
                }
                if (i11 == this.b) {
                    e7 = this.d & e(str);
                } else {
                    throw new IllegalStateException("Internal error on SymbolTable.rehash(): had " + this.b + " entries; now have " + i11 + ".");
                }
            } else if (!this.f569a) {
                String[] strArr3 = (String[]) this.e;
                int length2 = strArr3.length;
                String[] strArr4 = new String[length2];
                this.e = strArr4;
                System.arraycopy(strArr3, 0, strArr4, 0, length2);
                e[] eVarArr4 = (e[]) this.f;
                int length3 = eVarArr4.length;
                e[] eVarArr5 = new e[length3];
                this.f = eVarArr5;
                System.arraycopy(eVarArr4, 0, eVarArr5, 0, length3);
                this.f569a = true;
            }
            this.b++;
            String intern = str.intern();
            String[] strArr5 = (String[]) this.e;
            if (strArr5[e7] == null) {
                strArr5[e7] = intern;
                return;
            }
            int i16 = e7 >> 1;
            e[] eVarArr6 = (e[]) this.f;
            eVarArr6[i16] = new e(14, (Object) intern, (Object) eVarArr6[i16]);
        }
    }

    public void j(byte[] bArr) {
        int length = bArr.length;
        int i2 = this.b;
        int i7 = i2 + length;
        if ((length | i7) < 0 || length > bArr.length) {
            throw new IndexOutOfBoundsException("bytes.length " + bArr.length + "; 0..!" + i7);
        }
        if (this.f569a) {
            g(i7);
        } else if (i7 > ((byte[]) this.e).length) {
            i();
            throw null;
        }
        System.arraycopy(bArr, 0, (byte[]) this.e, i2, length);
        this.b = i7;
    }

    public void k(int i2) {
        int i7 = this.b;
        int i8 = i7 + 1;
        if (this.f569a) {
            g(i8);
        } else if (i8 > ((byte[]) this.e).length) {
            i();
            throw null;
        }
        ((byte[]) this.e)[i7] = (byte) i2;
        this.b = i8;
    }

    public void l(int i2) {
        int i7 = this.b;
        int i8 = i7 + 4;
        if (this.f569a) {
            g(i8);
        } else if (i8 > ((byte[]) this.e).length) {
            i();
            throw null;
        }
        byte[] bArr = (byte[]) this.e;
        bArr[i7] = (byte) i2;
        bArr[i7 + 1] = (byte) (i2 >> 8);
        bArr[i7 + 2] = (byte) (i2 >> 16);
        bArr[i7 + 3] = (byte) (i2 >> 24);
        this.b = i8;
    }

    public void m(int i2) {
        int i7 = this.b;
        int i8 = i7 + 2;
        if (this.f569a) {
            g(i8);
        } else if (i8 > ((byte[]) this.e).length) {
            i();
            throw null;
        }
        byte[] bArr = (byte[]) this.e;
        bArr[i7] = (byte) i2;
        bArr[i7 + 1] = (byte) (i2 >> 8);
        this.b = i8;
    }

    public void n(int i2) {
        int i7;
        int i8;
        if (this.f569a) {
            g(this.b + 5);
        }
        int i10 = i2 >> 7;
        if ((Integer.MIN_VALUE & i2) == 0) {
            i7 = 0;
        } else {
            i7 = -1;
        }
        int i11 = i10;
        int i12 = i2;
        int i13 = i11;
        boolean z = true;
        while (z) {
            if (i13 == i7 && (i13 & 1) == ((i12 >> 6) & 1)) {
                z = false;
            } else {
                z = true;
            }
            int i14 = i12 & 127;
            if (z) {
                i8 = 128;
            } else {
                i8 = 0;
            }
            k((byte) (i14 | i8));
            i12 = i13;
            i13 >>= 7;
        }
    }

    public int o(int i2) {
        if (this.f569a) {
            g(this.b + 5);
        }
        int i7 = this.b;
        while (true) {
            int i8 = i2;
            i2 >>>= 7;
            if (i2 != 0) {
                k((byte) ((i8 & 127) | 128));
            } else {
                k((byte) (i8 & 127));
                return this.b - i7;
            }
        }
    }

    public void p(int i2) {
        if (i2 >= 0) {
            int i7 = this.b + i2;
            if (this.f569a) {
                g(i7);
            } else if (i7 > ((byte[]) this.e).length) {
                i();
                throw null;
            }
            this.b = i7;
            return;
        }
        throw new IllegalArgumentException("count < 0");
    }

    public b(byte[] bArr, boolean z) {
        this.f569a = z;
        this.e = bArr;
        this.b = 0;
        this.f = null;
        this.f570c = 0;
        this.d = 0;
    }
}
