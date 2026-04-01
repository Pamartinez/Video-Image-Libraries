package P2;

import P0.b;
import S2.a;
import S2.h;
import S2.q;
import S2.t;
import S2.u;
import U2.c;
import c0.C0086a;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;
import t1.i;

/* renamed from: P2.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0056f {

    /* renamed from: a  reason: collision with root package name */
    public final z f593a;
    public final z b;

    /* renamed from: c  reason: collision with root package name */
    public final z f594c;
    public final z d;
    public final C e;
    public final C f;
    public final C g;

    /* renamed from: h  reason: collision with root package name */
    public final l f595h;

    /* renamed from: i  reason: collision with root package name */
    public final v f596i;

    /* renamed from: j  reason: collision with root package name */
    public final C0054d f597j;
    public final z k;
    public final z l;
    public final C m;
    public final D[] n;

    /* renamed from: o  reason: collision with root package name */
    public int f598o = -1;

    public C0056f(i iVar) {
        C c5 = new C(this, 3);
        this.m = c5;
        y yVar = y.NONE;
        z zVar = new z((String) null, this, 4, yVar);
        this.b = zVar;
        y yVar2 = y.TYPE;
        z zVar2 = new z("word_data", this, 4, yVar2);
        this.f593a = zVar2;
        z zVar3 = new z("string_data", this, 1, y.INSTANCE);
        this.d = zVar3;
        z zVar4 = new z((String) null, this, 1, yVar);
        this.k = zVar4;
        z zVar5 = new z("byte_data", this, 1, yVar2);
        this.l = zVar5;
        C c6 = new C(this, 1);
        this.e = c6;
        C c8 = new C(this, 2);
        this.f = c8;
        C c10 = new C(this, 0);
        this.g = c10;
        l lVar = new l(this);
        this.f595h = lVar;
        v vVar = new v(this);
        this.f596i = vVar;
        C0054d dVar = new C0054d(this);
        this.f597j = dVar;
        z zVar6 = new z("map", this, 4, yVar);
        this.f594c = zVar6;
        this.n = new D[]{c5, c6, c8, c10, lVar, vVar, dVar, zVar2, zVar, zVar3, zVar5, zVar4, zVar6};
    }

    public final void a(a aVar) {
        if (aVar instanceof t) {
            this.e.o((t) aVar);
        } else if (aVar instanceof u) {
            this.f.p((u) aVar);
        } else if (aVar instanceof q) {
            this.f596i.m((q) aVar);
        } else if (aVar instanceof h) {
            this.f595h.m((h) aVar);
        } else if (aVar == null) {
            throw new NullPointerException("cst == null");
        }
    }

    public final byte[] b() {
        c cVar;
        this.f597j.d();
        this.k.d();
        this.f593a.d();
        this.l.d();
        this.f596i.d();
        this.f595h.d();
        this.g.d();
        this.b.d();
        this.f.d();
        this.e.d();
        this.d.d();
        this.m.d();
        D[] dArr = this.n;
        int length = dArr.length;
        int i2 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            D d2 = dArr[i7];
            if (i8 < 0) {
                d2.getClass();
                throw new IllegalArgumentException("fileOffset < 0");
            } else if (d2.d < 0) {
                int i10 = d2.f578c - 1;
                int i11 = (~i10) & (i8 + i10);
                d2.d = i11;
                if (i11 >= i8) {
                    z zVar = this.f594c;
                    if (d2 == zVar) {
                        try {
                            r.l(dArr, zVar);
                            zVar.d();
                        } catch (RuntimeException e7) {
                            throw c.a(e7, "...while writing section " + i7);
                        }
                    }
                    if (d2 instanceof z) {
                        ((z) d2).m();
                    }
                    i8 = d2.h() + i11;
                    i7++;
                } else {
                    throw new RuntimeException(C0086a.i(i7, "bogus placement for section "));
                }
            } else {
                throw new RuntimeException("fileOffset already set");
            }
        }
        this.f598o = i8;
        byte[] bArr = new byte[i8];
        b bVar = new b(bArr, false);
        while (i2 < length) {
            try {
                D d3 = dArr[i2];
                int b5 = d3.b() - bVar.b;
                if (b5 >= 0) {
                    bVar.p(d3.b() - bVar.b);
                    d3.i(bVar);
                    i2++;
                } else {
                    throw new c("excess write of " + (-b5), (Exception) null);
                }
            } catch (RuntimeException e8) {
                if (e8 instanceof c) {
                    cVar = (c) e8;
                } else {
                    cVar = new c((String) null, e8);
                }
                String i12 = C0086a.i(i2, "...while writing section ");
                if (i12 != null) {
                    StringBuffer stringBuffer = cVar.d;
                    stringBuffer.append(i12);
                    if (!i12.endsWith("\n")) {
                        stringBuffer.append(10);
                    }
                    throw cVar;
                }
                throw new NullPointerException("str == null");
            }
        }
        if (bVar.b == this.f598o) {
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(bArr, 32, i8 - 32);
                try {
                    int digest = instance.digest(bArr, 12, 20);
                    if (digest == 20) {
                        Adler32 adler32 = new Adler32();
                        adler32.update(bArr, 12, i8 - 12);
                        int value = (int) adler32.getValue();
                        bArr[8] = (byte) value;
                        bArr[9] = (byte) (value >> 8);
                        bArr[10] = (byte) (value >> 16);
                        bArr[11] = (byte) (value >> 24);
                        return (byte[]) bVar.e;
                    }
                    throw new RuntimeException("unexpected digest write: " + digest + " bytes");
                } catch (DigestException e9) {
                    throw new RuntimeException(e9);
                }
            } catch (NoSuchAlgorithmException e10) {
                throw new RuntimeException(e10);
            }
        } else {
            throw new RuntimeException("foreshortened write");
        }
    }
}
