package V0;

import L1.d;
import Q0.c;
import Q0.e;
import Q0.h;
import Tf.m;
import W0.a;
import W0.k;
import e1.C0182b;
import h1.C0202b;
import h1.C0205e;
import i1.b;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class j extends h implements Serializable {
    public static final a m = new a((d) null, new a1.d(), C0205e.d, b.f1785p, Locale.getDefault(), c.b, new Object());
    public final e d;
    public final C0205e e;
    public final W0.c f;
    public final m g;

    /* renamed from: h  reason: collision with root package name */
    public l f867h;

    /* renamed from: i  reason: collision with root package name */
    public C0182b f868i;

    /* renamed from: j  reason: collision with root package name */
    public a f869j;
    public X0.d k;
    public LinkedHashSet l;

    /* JADX WARNING: type inference failed for: r8v0, types: [java.lang.Object, c1.a] */
    /* JADX WARNING: type inference failed for: r10v0, types: [W0.f, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r12v0, types: [L1.d, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r11v0, types: [W0.f, java.lang.Object] */
    public j(e eVar, com.fasterxml.jackson.dataformat.xml.ser.c cVar, X0.d dVar) {
        a aVar;
        X0.d dVar2;
        e eVar2 = eVar;
        new ConcurrentHashMap(64, 0.6f, 2);
        if (eVar2 == null) {
            this.d = new e(this);
        } else {
            this.d = eVar2;
            if (eVar2.a() == null) {
                eVar2.d = this;
            }
        }
        ? obj = new Object();
        ? obj2 = new Object();
        new ConcurrentHashMap(20, 0.8f, 4);
        this.e = C0205e.d;
        m mVar = new m();
        this.g = mVar;
        ? obj3 = new Object();
        a aVar2 = m;
        if (aVar2.e == obj3) {
            aVar = aVar2;
        } else {
            aVar = new a(obj3, aVar2.f, aVar2.d, aVar2.f884h, aVar2.f885i, aVar2.f886j, aVar2.g);
        }
        ? obj4 = new Object();
        W0.c cVar2 = new W0.c();
        this.f = cVar2;
        this.f867h = new l(aVar, obj, mVar, obj2, obj4);
        this.f869j = new a(aVar, obj, mVar, obj2, obj4, cVar2);
        this.d.getClass();
        l lVar = this.f867h;
        g gVar = g.SORT_PROPERTIES_ALPHABETICALLY;
        if (gVar.c(lVar.d)) {
            l lVar2 = this.f867h;
            int i2 = lVar2.d;
            int i7 = (~new g[]{gVar}[0].b()) & i2;
            this.f867h = i7 != i2 ? new l(lVar2, i7, lVar2.f872i) : lVar2;
            a aVar3 = this.f869j;
            int i8 = aVar3.d;
            int i10 = (~new g[]{gVar}[0].b()) & i8;
            this.f869j = i10 != i8 ? new a(aVar3, i10, aVar3.f865h) : aVar3;
        }
        if (cVar == null) {
            new HashMap(64);
            new AtomicReference();
        }
        if (dVar == null) {
            X0.b bVar = X0.b.k;
            dVar2 = new X0.d(0);
        } else {
            dVar2 = dVar;
        }
        this.k = dVar2;
        this.f868i = C0182b.e;
    }

    public final k a(C0202b bVar) {
        W0.c cVar = this.f;
        if (cVar.e == null) {
            cVar.e = new k[W0.c.f];
        }
        k kVar = cVar.e[bVar.ordinal()];
        if (kVar != null) {
            return kVar;
        }
        k[] kVarArr = cVar.e;
        int ordinal = bVar.ordinal();
        k kVar2 = new k();
        kVarArr[ordinal] = kVar2;
        return kVar2;
    }

    public final void b(i iVar) {
        String b;
        if (iVar == null) {
            throw new IllegalArgumentException("argument \"module\" is null");
        } else if (iVar.a() == null) {
            throw new IllegalArgumentException("Module without defined name");
        } else if (iVar.d() != null) {
            for (i b5 : Collections.EMPTY_LIST) {
                b(b5);
            }
            if (g.IGNORE_DUPLICATE_MODULE_REGISTRATIONS.c(this.f867h.d) && (b = iVar.b()) != null) {
                if (this.l == null) {
                    this.l = new LinkedHashSet();
                }
                if (!this.l.add(b)) {
                    return;
                }
            }
            iVar.c(new B1.b(7, this));
        } else {
            throw new IllegalArgumentException("Module without defined version");
        }
    }

    public j() {
        this((e) null, (com.fasterxml.jackson.dataformat.xml.ser.c) null, (X0.d) null);
    }
}
