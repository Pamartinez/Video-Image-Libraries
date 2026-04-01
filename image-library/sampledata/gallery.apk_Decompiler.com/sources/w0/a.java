package W0;

import L1.d;
import Q0.b;
import Q0.j;
import h1.C0205e;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Serializable {
    public final C0205e d;
    public final d e;
    public final j f;
    public final f g;

    /* renamed from: h  reason: collision with root package name */
    public final DateFormat f884h;

    /* renamed from: i  reason: collision with root package name */
    public final Locale f885i;

    /* renamed from: j  reason: collision with root package name */
    public final b f886j;

    static {
        TimeZone.getTimeZone("UTC");
    }

    public a(d dVar, j jVar, C0205e eVar, DateFormat dateFormat, Locale locale, b bVar, f fVar) {
        this.e = dVar;
        this.f = jVar;
        this.d = eVar;
        this.f884h = dateFormat;
        this.f885i = locale;
        this.f886j = bVar;
        this.g = fVar;
    }

    public final a a(j jVar) {
        if (this.f == jVar) {
            return this;
        }
        return new a(this.e, jVar, this.d, this.f884h, this.f885i, this.f886j, this.g);
    }
}
