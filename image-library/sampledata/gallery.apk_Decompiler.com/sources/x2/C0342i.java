package x2;

import K0.b;
import java.util.ArrayList;
import o1.C0246a;

/* renamed from: x2.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0342i extends C0246a {
    public final void X(b bVar, float f, float f5) {
        bVar.d(f5 * f, 180.0f, 90.0f);
        float f8 = f5 * 2.0f * f;
        C0349p pVar = new C0349p(0.0f, 0.0f, f8, f8);
        pVar.f = 180.0f;
        pVar.g = 90.0f;
        ((ArrayList) bVar.f).add(pVar);
        C0347n nVar = new C0347n(pVar);
        bVar.a(180.0f);
        ((ArrayList) bVar.g).add(nVar);
        bVar.d = 270.0f;
        float f10 = (0.0f + f8) * 0.5f;
        float f11 = (f8 - 0.0f) / 2.0f;
        double d = (double) 270.0f;
        bVar.b = (((float) Math.cos(Math.toRadians(d))) * f11) + f10;
        bVar.f377c = (f11 * ((float) Math.sin(Math.toRadians(d)))) + f10;
    }
}
