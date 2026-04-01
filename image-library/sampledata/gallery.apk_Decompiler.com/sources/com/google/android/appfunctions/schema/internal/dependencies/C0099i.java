package com.google.android.appfunctions.schema.internal.dependencies;

/* renamed from: com.google.android.appfunctions.schema.internal.dependencies.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0099i implements Cloneable {
    public final C0102l d;
    public C0102l e;

    public C0099i(C0102l lVar) {
        this.d = lVar;
        if (!lVar.e()) {
            this.e = (C0102l) lVar.b(C0101k.zzd);
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    public final C0102l a() {
        C0102l b = b();
        b.getClass();
        byte byteValue = ((Byte) b.b(C0101k.zza)).byteValue();
        boolean z = true;
        if (byteValue != 1) {
            if (byteValue == 0) {
                z = false;
            } else {
                z = J.f1206c.a(b.getClass()).e(b);
                b.b(C0101k.zzb);
            }
        }
        if (z) {
            return b;
        }
        throw new RuntimeException("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final C0102l b() {
        if (!this.e.e()) {
            return this.e;
        }
        C0102l lVar = this.e;
        lVar.getClass();
        J.f1206c.a(lVar.getClass()).f(lVar);
        lVar.f();
        return this.e;
    }

    public final void c() {
        if (!this.e.e()) {
            C0102l lVar = (C0102l) this.d.b(C0101k.zzd);
            J.f1206c.a(lVar.getClass()).d(lVar, this.e);
            this.e = lVar;
        }
    }

    public final Object clone() {
        C0099i iVar = (C0099i) this.d.b(C0101k.zze);
        iVar.e = b();
        return iVar;
    }
}
