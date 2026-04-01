package E0;

import D0.a;
import D0.b;
import D0.g;
import D0.h;
import x0.C0332j;
import x0.w;
import z0.c;
import z0.q;
import z0.r;
import z0.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o implements b {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f144a = 1;
    public final Object b;

    /* renamed from: c  reason: collision with root package name */
    public final b f145c;
    public final boolean d;
    public final h e;
    public final Object f;

    public o(String str, b bVar, b bVar2, g gVar, boolean z) {
        this.b = str;
        this.f145c = bVar;
        this.e = bVar2;
        this.f = gVar;
        this.d = z;
    }

    public final c a(w wVar, C0332j jVar, F0.c cVar) {
        switch (this.f144a) {
            case 0:
                return new q(wVar, cVar, this);
            case 1:
                return new r(wVar, cVar, this);
            default:
                return new v(cVar, this);
        }
    }

    public String toString() {
        switch (this.f144a) {
            case 0:
                return "RectangleShape{position=" + this.e + ", size=" + ((h) this.f) + '}';
            case 2:
                return "Trim Path: {start: " + this.f145c + ", end: " + ((b) this.e) + ", offset: " + ((b) this.f) + "}";
            default:
                return super.toString();
        }
    }

    public o(String str, h hVar, a aVar, b bVar, boolean z) {
        this.b = str;
        this.e = hVar;
        this.f = aVar;
        this.f145c = bVar;
        this.d = z;
    }

    public o(String str, y yVar, b bVar, b bVar2, b bVar3, boolean z) {
        this.b = yVar;
        this.f145c = bVar;
        this.e = bVar2;
        this.f = bVar3;
        this.d = z;
    }
}
