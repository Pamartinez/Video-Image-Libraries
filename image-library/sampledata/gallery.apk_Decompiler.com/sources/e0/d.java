package E0;

import D0.a;
import android.graphics.Path;
import x0.C0332j;
import x0.w;
import z0.c;
import z0.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements b {

    /* renamed from: a  reason: collision with root package name */
    public final f f127a;
    public final Path.FillType b;

    /* renamed from: c  reason: collision with root package name */
    public final a f128c;
    public final a d;
    public final a e;
    public final a f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f129h;

    public d(String str, f fVar, Path.FillType fillType, a aVar, a aVar2, a aVar3, a aVar4, boolean z) {
        this.f127a = fVar;
        this.b = fillType;
        this.f128c = aVar;
        this.d = aVar2;
        this.e = aVar3;
        this.f = aVar4;
        this.g = str;
        this.f129h = z;
    }

    public final c a(w wVar, C0332j jVar, F0.c cVar) {
        return new h(wVar, jVar, cVar, this);
    }
}
