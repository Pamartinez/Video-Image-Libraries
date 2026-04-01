package F0;

import D0.a;
import D0.b;
import D0.e;
import D0.g;
import E0.h;
import i.C0212a;
import java.util.List;
import java.util.Locale;
import x0.C0332j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public final List f203a;
    public final C0332j b;

    /* renamed from: c  reason: collision with root package name */
    public final String f204c;
    public final long d;
    public final g e;
    public final long f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final List f205h;

    /* renamed from: i  reason: collision with root package name */
    public final g f206i;

    /* renamed from: j  reason: collision with root package name */
    public final int f207j;
    public final int k;
    public final int l;
    public final float m;
    public final float n;

    /* renamed from: o  reason: collision with root package name */
    public final float f208o;

    /* renamed from: p  reason: collision with root package name */
    public final float f209p;
    public final a q;
    public final e r;
    public final b s;
    public final List t;
    public final h u;
    public final boolean v;
    public final G0.e w;

    /* renamed from: x  reason: collision with root package name */
    public final B0.a f210x;
    public final h y;

    public i(List list, C0332j jVar, String str, long j2, g gVar, long j3, String str2, List list2, g gVar2, int i2, int i7, int i8, float f5, float f8, float f10, float f11, a aVar, e eVar, List list3, h hVar, b bVar, boolean z, G0.e eVar2, B0.a aVar2, h hVar2) {
        this.f203a = list;
        this.b = jVar;
        this.f204c = str;
        this.d = j2;
        this.e = gVar;
        this.f = j3;
        this.g = str2;
        this.f205h = list2;
        this.f206i = gVar2;
        this.f207j = i2;
        this.k = i7;
        this.l = i8;
        this.m = f5;
        this.n = f8;
        this.f208o = f10;
        this.f209p = f11;
        this.q = aVar;
        this.r = eVar;
        this.t = list3;
        this.u = hVar;
        this.s = bVar;
        this.v = z;
        this.w = eVar2;
        this.f210x = aVar2;
        this.y = hVar2;
    }

    public final String a(String str) {
        int i2;
        StringBuilder s5 = C0212a.s(str);
        s5.append(this.f204c);
        s5.append("\n");
        long j2 = this.f;
        C0332j jVar = this.b;
        i iVar = (i) jVar.f2059i.get(j2);
        if (iVar != null) {
            s5.append("\t\tParents: ");
            s5.append(iVar.f204c);
            for (i iVar2 = (i) jVar.f2059i.get(iVar.f); iVar2 != null; iVar2 = (i) jVar.f2059i.get(iVar2.f)) {
                s5.append("->");
                s5.append(iVar2.f204c);
            }
            s5.append(str);
            s5.append("\n");
        }
        List list = this.f205h;
        if (!list.isEmpty()) {
            s5.append(str);
            s5.append("\tMasks: ");
            s5.append(list.size());
            s5.append("\n");
        }
        int i7 = this.f207j;
        if (!(i7 == 0 || (i2 = this.k) == 0)) {
            s5.append(str);
            s5.append("\tBackground: ");
            s5.append(String.format(Locale.US, "%dx%d %X\n", new Object[]{Integer.valueOf(i7), Integer.valueOf(i2), Integer.valueOf(this.l)}));
        }
        List list2 = this.f203a;
        if (!list2.isEmpty()) {
            s5.append(str);
            s5.append("\tShapes:\n");
            for (Object next : list2) {
                s5.append(str);
                s5.append("\t\t");
                s5.append(next);
                s5.append("\n");
            }
        }
        return s5.toString();
    }

    public final String toString() {
        return a("");
    }
}
