package P2;

import P0.b;
import S2.a;
import S2.c;
import S2.e;
import S2.f;
import S2.g;
import S2.i;
import S2.j;
import S2.k;
import S2.n;
import S2.o;
import S2.s;
import S2.u;
import U2.d;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: P2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0052b extends A {

    /* renamed from: h  reason: collision with root package name */
    public final u f583h;

    /* renamed from: i  reason: collision with root package name */
    public final ArrayList f584i;

    /* renamed from: j  reason: collision with root package name */
    public final HashMap f585j;
    public final ArrayList k;
    public final ArrayList l;
    public final ArrayList m;
    public c n;

    /* renamed from: o  reason: collision with root package name */
    public byte[] f586o;

    public C0052b(u uVar) {
        super(1, -1);
        if (uVar != null) {
            this.f583h = uVar;
            this.f584i = new ArrayList(20);
            this.f585j = new HashMap(40);
            this.k = new ArrayList(20);
            this.l = new ArrayList(20);
            this.m = new ArrayList(20);
            this.n = null;
            return;
        }
        throw new NullPointerException("thisClass == null");
    }

    public static void l(C0056f fVar, b bVar, String str, ArrayList arrayList) {
        int size = arrayList.size();
        if (size != 0) {
            if (bVar.d()) {
                bVar.b(0, "  " + str + NumericEnum.SEP);
            }
            int i2 = 0;
            for (int i7 = 0; i7 < size; i7++) {
                i2 = ((C0059i) arrayList.get(i7)).c(fVar, bVar, i2, i7);
            }
        }
    }

    public static void n(b bVar, String str, int i2) {
        if (bVar.d()) {
            bVar.c(String.format("  %-21s %08x", new Object[]{str.concat("_size:"), Integer.valueOf(i2)}));
        }
        bVar.o(i2);
    }

    public final void a(C0056f fVar) {
        ArrayList arrayList = this.f584i;
        if (!arrayList.isEmpty()) {
            o();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C0058h hVar = (C0058h) it.next();
                hVar.getClass();
                fVar.f595h.m(hVar.e);
            }
        }
        ArrayList arrayList2 = this.k;
        if (!arrayList2.isEmpty()) {
            Collections.sort(arrayList2);
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                C0058h hVar2 = (C0058h) it2.next();
                hVar2.getClass();
                fVar.f595h.m(hVar2.e);
            }
        }
        ArrayList arrayList3 = this.l;
        if (!arrayList3.isEmpty()) {
            Collections.sort(arrayList3);
            Iterator it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                C0060j jVar = (C0060j) it3.next();
                jVar.getClass();
                v vVar = fVar.f596i;
                z zVar = fVar.f593a;
                vVar.m(jVar.e);
                C0055e eVar = jVar.f;
                if (eVar != null) {
                    zVar.k(eVar);
                }
            }
        }
        ArrayList arrayList4 = this.m;
        if (!arrayList4.isEmpty()) {
            Collections.sort(arrayList4);
            Iterator it4 = arrayList4.iterator();
            while (it4.hasNext()) {
                C0060j jVar2 = (C0060j) it4.next();
                jVar2.getClass();
                v vVar2 = fVar.f596i;
                z zVar2 = fVar.f593a;
                vVar2.m(jVar2.e);
                C0055e eVar2 = jVar2.f;
                if (eVar2 != null) {
                    zVar2.k(eVar2);
                }
            }
        }
    }

    public final q b() {
        return q.TYPE_CLASS_DATA_ITEM;
    }

    public final void i(z zVar, int i2) {
        b bVar = new b();
        m(zVar.b, bVar);
        int i7 = bVar.b;
        byte[] bArr = new byte[i7];
        System.arraycopy((byte[]) bVar.e, 0, bArr, 0, i7);
        this.f586o = bArr;
        j(i7);
    }

    public final boolean isEmpty() {
        if (!this.f584i.isEmpty() || !this.k.isEmpty() || !this.l.isEmpty() || !this.m.isEmpty()) {
            return false;
        }
        return true;
    }

    public final void k(C0056f fVar, b bVar) {
        if (bVar.d()) {
            m(fVar, bVar);
        } else {
            bVar.j(this.f586o);
        }
    }

    public final void m(C0056f fVar, b bVar) {
        boolean d = bVar.d();
        if (d) {
            bVar.b(0, g() + " class data for " + this.f583h.d.a());
        }
        ArrayList arrayList = this.f584i;
        n(bVar, "static_fields", arrayList.size());
        ArrayList arrayList2 = this.k;
        n(bVar, "instance_fields", arrayList2.size());
        ArrayList arrayList3 = this.l;
        n(bVar, "direct_methods", arrayList3.size());
        ArrayList arrayList4 = this.m;
        n(bVar, "virtual_methods", arrayList4.size());
        l(fVar, bVar, "static_fields", arrayList);
        l(fVar, bVar, "instance_fields", arrayList2);
        l(fVar, bVar, "direct_methods", arrayList3);
        l(fVar, bVar, "virtual_methods", arrayList4);
        if (d) {
            bVar.f();
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [U2.d, Nf.f, S2.b] */
    public final c o() {
        HashMap hashMap;
        c cVar;
        a aVar;
        if (this.n == null) {
            ArrayList arrayList = this.f584i;
            if (arrayList.size() != 0) {
                Collections.sort(arrayList);
                int size = arrayList.size();
                while (true) {
                    hashMap = this.f585j;
                    if (size <= 0) {
                        break;
                    }
                    a aVar2 = (a) hashMap.get((C0058h) arrayList.get(size - 1));
                    if (aVar2 instanceof n) {
                        if (((n) aVar2).h() != 0) {
                            break;
                        }
                    } else if (aVar2 != null) {
                        break;
                    }
                    size--;
                }
                if (size == 0) {
                    cVar = null;
                } else {
                    ? dVar = new d(size);
                    for (int i2 = 0; i2 < size; i2++) {
                        C0058h hVar = (C0058h) arrayList.get(i2);
                        a aVar3 = (a) hashMap.get(hVar);
                        if (aVar3 == null) {
                            T2.c type = hVar.e.getType();
                            switch (type.e) {
                                case 1:
                                    aVar = S2.d.e;
                                    break;
                                case 2:
                                    aVar = e.e;
                                    break;
                                case 3:
                                    aVar = f.e;
                                    break;
                                case 4:
                                    aVar = g.e;
                                    break;
                                case 5:
                                    aVar = i.e;
                                    break;
                                case 6:
                                    aVar = j.f;
                                    break;
                                case 7:
                                    aVar = o.e;
                                    break;
                                case 8:
                                    aVar = s.e;
                                    break;
                                case 9:
                                    aVar = k.d;
                                    break;
                                default:
                                    throw new UnsupportedOperationException("no zero for type: " + type.a());
                            }
                            aVar3 = aVar;
                        }
                        dVar.e(i2, aVar3);
                    }
                    dVar.d = false;
                    cVar = new c(dVar);
                }
                this.n = cVar;
            }
        }
        return this.n;
    }
}
